package org.sustudent.cherry.common.core.redis;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author yiyi.su
 */
@Configuration
@EnableCaching
public class RedisCacheConfig {


  @Value("${spring.application.name}")
  private String cachePrefix;

  @Bean
  public RedisTemplate<String, Object> redisTemplate(
      RedisConnectionFactory redisConnectionFactory) {
    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(redisConnectionFactory);
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
    redisTemplate.setHashKeySerializer(new StringRedisSerializer());
    redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
    redisTemplate.afterPropertiesSet();
    return redisTemplate;
  }


  @Bean
  public RedisCacheManager cacheManager(RedisTemplate redisTemplate) {

    if (StringUtils.isNotBlank(cachePrefix)) {
      cachePrefix += "::";
    }
    RedisCacheConfiguration configuration = RedisCacheConfiguration
        .defaultCacheConfig()
        .serializeValuesWith(
            RedisSerializationContext
                .SerializationPair
                .fromSerializer(redisTemplate.getValueSerializer())
        ).computePrefixWith(cacheName -> cachePrefix + cacheName + "::");

    return RedisCacheManager
        .builder(
            RedisCacheWriter
                .nonLockingRedisCacheWriter(redisTemplate.getConnectionFactory())
        ).cacheDefaults(configuration).build();
  }
}

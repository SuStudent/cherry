package org.sustudent.cherry.common.security.properties;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@RefreshScope
@ConditionalOnExpression("!'${security.ignore}'.isEmpty()")
@ConfigurationProperties(prefix = "security.ignore")
public class SecurityIgnoreProperties {

  private List<String> urls = new ArrayList<>();

  private List<String> clients = new ArrayList<>();
}

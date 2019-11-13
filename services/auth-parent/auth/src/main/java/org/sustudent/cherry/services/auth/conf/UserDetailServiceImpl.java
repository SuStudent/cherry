package org.sustudent.cherry.services.auth.conf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.sustudent.cherry.services.user.api.feign.UserServiceClient;

@Slf4j
@Service
public class UserDetailServiceImpl implements UserDetailsService {

  @Autowired
  private UserServiceClient userClientService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userClientService.findUserByUsername(username).getData();
  }
}

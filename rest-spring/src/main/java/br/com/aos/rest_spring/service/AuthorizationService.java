package br.com.aos.rest_spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import br.com.aos.rest_spring.repository.UserRepository;

@Service
public class AuthorizationService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String email) {
    return userRepository.findByEmail(email).get();
  }

}

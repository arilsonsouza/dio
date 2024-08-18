package br.com.aos.rest_spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.aos.rest_spring.entity.User;
import br.com.aos.rest_spring.repository.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public User save(User user) {
    return userRepository.save(user);
  }

  public User findByEmail(String email) {
    return userRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }

  public Boolean existsByEmail(String email) {
    try {
      findByEmail(email);
      return true;
    } catch (UsernameNotFoundException e) {
      return false;
    }
  }
}

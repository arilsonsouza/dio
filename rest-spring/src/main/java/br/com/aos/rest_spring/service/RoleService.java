package br.com.aos.rest_spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aos.rest_spring.entity.ERole;
import br.com.aos.rest_spring.entity.Role;
import br.com.aos.rest_spring.repository.RoleRepository;

@Service
public class RoleService {
  @Autowired
  private RoleRepository roleRepository;

  public Role findByName(ERole role) {
    return roleRepository.findByName(role)
        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
  }
}

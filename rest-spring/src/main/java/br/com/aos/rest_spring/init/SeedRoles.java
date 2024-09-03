package br.com.aos.rest_spring.init;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.aos.rest_spring.entity.ERole;
import br.com.aos.rest_spring.entity.Role;
import br.com.aos.rest_spring.repository.RoleRepository;

@Component
public class SeedRoles implements CommandLineRunner {
  @Autowired
  private RoleRepository roleRepository;

  @Override
  public void run(String... args) throws Exception {
    Set<Role> roles = new HashSet<>();
    if (!roleRepository.findByName(ERole.ROLE_USER).isPresent()) {
      roles.add(new Role(ERole.ROLE_USER));
    }

    if (!roleRepository.findByName(ERole.ROLE_ADMIN).isPresent()) {
      roles.add(new Role(ERole.ROLE_ADMIN));
    }
    roleRepository.saveAll(roles);
  }
}

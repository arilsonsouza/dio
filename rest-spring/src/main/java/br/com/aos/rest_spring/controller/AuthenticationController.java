package br.com.aos.rest_spring.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aos.rest_spring.config.security.JwtTokenService;
import br.com.aos.rest_spring.controller.DTO.ApiResponseDTO;
import br.com.aos.rest_spring.controller.DTO.auth.AuthenticationRequestDTO;
import br.com.aos.rest_spring.controller.DTO.auth.AuthenticationResponseDTO;
import br.com.aos.rest_spring.controller.DTO.auth.SignInDTO;
import br.com.aos.rest_spring.controller.DTO.user.RegisterDTO;
import br.com.aos.rest_spring.entity.ERole;
import br.com.aos.rest_spring.entity.Role;
import br.com.aos.rest_spring.entity.User;
import br.com.aos.rest_spring.service.RoleService;
import br.com.aos.rest_spring.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  private UserService userService;

  @Autowired
  private RoleService roleService;

  @Autowired
  private JwtTokenService jwtTokenService;

  @PostMapping("/signin")
  public ResponseEntity<ApiResponseDTO<SignInDTO>> signIn(
      @RequestBody @Valid AuthenticationRequestDTO data,
      HttpServletRequest request) {
    var usernamePasswod = new UsernamePasswordAuthenticationToken(data.email(),
        data.password());
    var auth = this.authenticationManager.authenticate(usernamePasswod);

    User user = (User) auth.getPrincipal();

    String jwtToken = jwtTokenService.generateToken(user);

    AuthenticationResponseDTO responseDTO = new AuthenticationResponseDTO(jwtToken, "Bearer");

    ApiResponseDTO<SignInDTO> apiResponse = ApiResponseDTO.success(new SignInDTO(responseDTO),
        "User logged successfully");

    return ResponseEntity.ok().body(apiResponse);
  }

  @PostMapping("/signup")
  public ResponseEntity<ApiResponseDTO<?>> signUp(@RequestBody @Valid RegisterDTO data) {
    if (userService.existsByEmail(data.email())) {

      ApiResponseDTO<?> apiResponse = ApiResponseDTO.error(null, "User already exists");

      return ResponseEntity.badRequest().body(apiResponse);
    }

    String encryptedPassword = encoder.encode(data.password());

    Set<Role> roles = new HashSet<>();
    Role userRole = roleService.findByName(ERole.ROLE_USER);

    roles.add(userRole);

    User newUser = new User(data.name(), data.email(), encryptedPassword, roles);

    userService.save(newUser);

    ApiResponseDTO<?> apiResponse = ApiResponseDTO.success(null, "User registered successfully");

    return ResponseEntity.ok().body(apiResponse);
  }
}

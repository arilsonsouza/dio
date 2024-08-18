package br.com.aos.rest_spring.controller.DTO.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record AuthenticationRequestDTO(@NotBlank @Email String email, @NotBlank @Min(6) String password) {

}

package br.com.aos.rest_spring.controller.DTO.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthenticationResponseDTO(@JsonProperty("access_token") String accessToken,
    @JsonProperty("token_type") String tokenType) {
}

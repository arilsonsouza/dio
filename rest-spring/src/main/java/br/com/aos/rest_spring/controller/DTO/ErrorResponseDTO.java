package br.com.aos.rest_spring.controller.DTO;

import java.util.Map;

public record ErrorResponseDTO(Map<String, String> errors) {

}

package br.com.aos.rest_spring.controller.DTO.product;

import br.com.aos.rest_spring.entity.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductDTO(Long id, @NotBlank(message = "name is mandatory") String name,
    @NotNull(message = "price is mandatory") double price,
    @NotBlank(message = "description is mandatory") String description) {

  public static ProductDTO fromEntity(Product product) {
    return new ProductDTO(product.getId(), product.getName(), product.getPrice(), product.getDescription());
  }

  public Product toEntity() {
    return Product.builder()
        .name(name)
        .price(price)
        .description(description)
        .build();
  }

}

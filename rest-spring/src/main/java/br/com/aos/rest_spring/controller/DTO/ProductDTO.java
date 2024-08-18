package br.com.aos.rest_spring.controller.DTO;

import br.com.aos.rest_spring.entity.Product;

public record ProductDTO(Long id, String name, double price, String description) {

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

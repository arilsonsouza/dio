package br.com.aos.rest_spring.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aos.rest_spring.controller.DTO.ProductDTO;
import br.com.aos.rest_spring.entity.Product;
import br.com.aos.rest_spring.service.ProductService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/products")
public class ProductController {

  @Autowired
  private ProductService productService;

  @PostMapping
  public ResponseEntity<ProductDTO> save(@Valid @RequestBody ProductDTO productDTO) {
    Product product = productService.save(productDTO.toEntity());
    return ResponseEntity.ok().body(ProductDTO.fromEntity(product));
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
    Product product = productService.findById(id);
    return ResponseEntity.ok().body(ProductDTO.fromEntity(product));
  }

  @GetMapping
  public ResponseEntity<List<ProductDTO>> findAll() {
    List<ProductDTO> products = productService.findAll().stream().map(ProductDTO::fromEntity)
        .collect(Collectors.toList());

    return ResponseEntity.ok().body(products);
  }
}

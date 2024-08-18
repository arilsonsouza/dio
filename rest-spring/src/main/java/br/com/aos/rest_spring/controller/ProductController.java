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

import br.com.aos.rest_spring.controller.DTO.ApiResponseDTO;
import br.com.aos.rest_spring.controller.DTO.product.ProductDTO;
import br.com.aos.rest_spring.controller.DTO.product.ProductResponseDTO;
import br.com.aos.rest_spring.controller.DTO.product.ProductsDTO;
import br.com.aos.rest_spring.entity.Product;
import br.com.aos.rest_spring.service.ProductService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/products")
public class ProductController {

  @Autowired
  private ProductService productService;

  @PostMapping
  public ResponseEntity<ApiResponseDTO<ProductResponseDTO>> save(@Valid @RequestBody ProductDTO productDTO) {
    Product product = productService.save(productDTO.toEntity());
    ProductResponseDTO response = new ProductResponseDTO(ProductDTO.fromEntity(product));
    return ResponseEntity.ok()
        .body(ApiResponseDTO.success(response, "Product created successfuly"));
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<ApiResponseDTO<ProductResponseDTO>> findById(@PathVariable Long id) {
    Product product = productService.findById(id);
    ProductResponseDTO response = new ProductResponseDTO(ProductDTO.fromEntity(product));
    return ResponseEntity.ok().body(ApiResponseDTO.success(response, null));
  }

  @GetMapping
  public ResponseEntity<ApiResponseDTO<ProductsDTO>> findAll() {
    List<ProductDTO> products = productService.findAll().stream().map(ProductDTO::fromEntity)
        .collect(Collectors.toList());

    ProductsDTO response = new ProductsDTO(products);
    return ResponseEntity.ok().body(ApiResponseDTO.success(response, null));
  }
}

package br.com.aos.rest_spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aos.rest_spring.entity.Product;
import br.com.aos.rest_spring.repository.ProductRepository;

@Service
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  public Product save(Product product) {
    return productRepository.save(product);
  }

  public Product findById(Long id) {
    return productRepository.findById(id).orElse(null);
  }

  public List<Product> findAll() {
    return productRepository.findAll();
  }
}

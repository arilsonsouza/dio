package br.com.aos.rest_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.aos.rest_spring.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}

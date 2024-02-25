package br.com.fiap.techchallenge.diegopinho.msstorage.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.techchallenge.diegopinho.msstorage.dtos.ProductDTO;
import br.com.fiap.techchallenge.diegopinho.msstorage.entities.Product;
import br.com.fiap.techchallenge.diegopinho.msstorage.services.ProductService;
import br.com.fiap.techchallenge.diegopinho.msstorage.utils.DTOValidator;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/products")
public class ProductController {

  @Autowired
  private ProductService productService;

  @Autowired
  private DTOValidator validator;

  @GetMapping
  public ResponseEntity<List<Product>> getAll() {
    List<Product> products = productService.getAll();
    return ResponseEntity.ok(products);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getById(@PathVariable("id") Long id) {
    try {
      Product product = this.productService.getById(id);
      return ResponseEntity.ok(product);
    } catch (EntityNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }

  @PostMapping
  public ResponseEntity<?> create(@RequestBody ProductDTO dto) {
    Map<Object, Object> violations = validator.check(dto);
    if (!violations.isEmpty()) {
      return ResponseEntity.badRequest().body(violations);
    }

    Product product = this.productService.create(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(product);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> update(@RequestBody ProductDTO dto, @PathVariable("id") Long id) {
    try {
      this.productService.update(id, dto);
      return ResponseEntity.ok().build();
    } catch (EntityNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable("id") Long id) {
    try {
      this.productService.delete(id);
      return ResponseEntity.ok().build();
    } catch (EntityNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot remove resource in use.");
    }
  }

}

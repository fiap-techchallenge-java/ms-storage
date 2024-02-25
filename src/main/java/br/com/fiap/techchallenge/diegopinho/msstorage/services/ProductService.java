package br.com.fiap.techchallenge.diegopinho.msstorage.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.techchallenge.diegopinho.msstorage.dtos.ProductDTO;
import br.com.fiap.techchallenge.diegopinho.msstorage.entities.Product;
import br.com.fiap.techchallenge.diegopinho.msstorage.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  public List<Product> getAll() {
    return this.productRepository.findAll();
  }

  public Product getById(Long id) {
    return this.productRepository
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Product Not Found!"));
  }

  public Product create(ProductDTO dto) {
    return this.productRepository.save(dto.toProduct());
  }

  public Product update(long id, ProductDTO dto) {
    this.getById(id);

    Product product = dto.toProduct();
    product.setId(id);

    return this.productRepository.save(product);
  }

  public void delete(Long id) {
    this.getById(id);
    this.productRepository.deleteById(id);
  }

}

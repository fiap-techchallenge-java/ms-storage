package br.com.fiap.techchallenge.diegopinho.msstorage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.techchallenge.diegopinho.msstorage.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}

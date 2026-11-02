package com.iset.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.iset.demo.entities.Product;

@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product,Long> {
    
}


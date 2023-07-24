package com.practica1.backend.model.repository;


import com.practica1.backend.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);

    Page<Product> findByCategory(String nameCategory, Pageable pageable);


    @Query("SELECT p FROM Product p")
    Page<Product> findWithPage(Pageable pageable);
}

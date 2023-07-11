package com.practica1.backend.model.repository;

import com.practica1.backend.model.entity.InvoiceProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceProductRepository extends JpaRepository<InvoiceProduct, Long> {
}

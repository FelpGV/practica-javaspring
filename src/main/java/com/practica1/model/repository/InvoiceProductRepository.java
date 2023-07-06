package com.practica1.model.repository;

import com.practica1.model.entity.InvoiceProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceProductRepository extends JpaRepository<InvoiceProduct, Long> {
}

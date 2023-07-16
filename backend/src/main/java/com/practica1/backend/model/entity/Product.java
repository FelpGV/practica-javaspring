package com.practica1.backend.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Long idProduct;

    @Column(length = 45, nullable = false, unique = true)
    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @Column(length = 150, nullable = false)
    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(length = 30, nullable = false)
    private String category;

    @Column(nullable = false)
    private Integer stock;

    @Column(length = 150, nullable = false)
    private String image;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<InvoiceProduct> invoiceProduct;
}
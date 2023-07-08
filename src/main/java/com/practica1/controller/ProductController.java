package com.practica1.controller;

import com.practica1.model.entity.Product;
import com.practica1.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all Products")
    public Iterable<Product> getAll() {
        return productService.getAll();
    }

    @RequestMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get a Product by id")
    public Product getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @PostMapping("/")
    @Operation(summary = "Create a new Product")
    public ResponseEntity<Product> addProduct(Product product) {
        try {
            Product productSaved = productService.addProduct(product);
            return ResponseEntity.ok(productSaved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a Product by id")
    public ResponseEntity<Product> updateProduct(@PathVariable long id, @RequestBody Product product) {
        try {
            Product productUpdated = productService.updateProduct(id, product);
            return ResponseEntity.ok(productUpdated);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Product by id")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }


}

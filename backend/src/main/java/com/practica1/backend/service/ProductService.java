package com.practica1.backend.service;

import com.practica1.backend.model.entity.Product;
import com.practica1.backend.model.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll() {
        List<Product> products = productRepository.findAll();
        try {
            return products;
        } catch (Exception e) {
            throw new EntityNotFoundException("No products found");
        }
    }

    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }

    public Product addProduct(Product product) {
        Optional<Product> existingProduct = productRepository.findByName(product.getName());
        if (existingProduct.isPresent()) {
            throw new DataIntegrityViolationException("Product with name " + product.getName() + " already exists");
        }
        return productRepository.save(product);
    }

    public Product updateProduct(long id, Product product) {
        Optional<Product> productToUpdate = productRepository.findById(id);
        Optional<Product> existingProduct = productRepository.findByName(product.getName());
        if (existingProduct.isPresent()) {
            throw new DataIntegrityViolationException("Product with name " + product.getName() + " already exists");
        }
        return productToUpdate.map(product1 -> {
            product1.setName(product.getName());
            product1.setPrice(product.getPrice());
            product1.setStock(product.getStock());
            return productRepository.save(product1);
        }).orElseThrow(() -> new EntityNotFoundException("Product not found with id " + id));
    }

    public void updateProductStock(long id, int newStock) {
        Optional<Product> productToUpdate = productRepository.findById(id);

        if (productToUpdate.isPresent()) {
            Product productUpdated = productToUpdate.get();
            productUpdated.setStock(productUpdated.getStock() - newStock);
            if (productUpdated.getStock() < 0) {
                throw new DataIntegrityViolationException("Product with id " + id + " has not enough stock");
            }
            productRepository.save(productUpdated);
        }
    }

    public void deleteProduct(Long id) {
        try {
            productRepository.deleteById(id);
        } catch (Exception e) {
            throw new EntityNotFoundException("Product not found with id " + id);
        }
    }


}

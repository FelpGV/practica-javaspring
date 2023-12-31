package com.practica1.backend.service;

import com.practica1.backend.model.entity.Product;
import com.practica1.backend.model.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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


    public void addImage() {
        List<Product> products = productRepository.findAll();
        for(Product product : products){
            String category = product.getCategory();
            product.setImage(switch (category) {
                case "Disco duro" -> "https://i.imgur.com/67fm5pQ.png";
                case "Mouse" -> "https://i.imgur.com/LTYOKu2.png";
                case "Teclado" -> "https://i.imgur.com/crFmLL4.png";
                case "Pantalla" -> "https://i.imgur.com/RunMaR5.png";
                default -> null;  // Or another default value
            });
            productRepository.save(product);
        }
    }


    public Page<Product> getProductsByCategory(String nameCategory, Pageable pageable) {
        Page<Product> products = productRepository.findByCategory(nameCategory, pageable);
        try {
            return products;
        } catch (Exception e) {
            throw new EntityNotFoundException("No products " + nameCategory + " found");
        }
    }

    public Page<Product> getProductPage(Pageable pageable) {
        Page<Product> products = productRepository.findWithPage(pageable);
        try {
            return products;
        } catch (Exception e) {
            throw new EntityNotFoundException("No products found");
        }
    }
}

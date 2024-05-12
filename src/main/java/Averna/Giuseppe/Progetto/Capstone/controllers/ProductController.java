package Averna.Giuseppe.Progetto.Capstone.controllers;

import Averna.Giuseppe.Progetto.Capstone.entities.Product;
import Averna.Giuseppe.Progetto.Capstone.exceptions.ResourceNotFoundException;
import Averna.Giuseppe.Progetto.Capstone.repositories.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }
    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }
    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prodotto con Id " + id + " Snon trovato"));
    }
    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(product -> {
                    productRepository.delete(product);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Prodotto con Id " + id + " non trovato"));
    }
    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product productRequest) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(productRequest.getName());
                    product.setDescription(productRequest.getDescription());
                    product.setPrice(productRequest.getPrice());
                    product.setImageUrl(productRequest.getImageUrl());
                    return productRepository.save(product);
                }).orElseThrow(() -> new ResourceNotFoundException("Prodotto con Id " + id + " non trovato"));
    }
}

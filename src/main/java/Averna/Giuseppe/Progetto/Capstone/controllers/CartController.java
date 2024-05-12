package Averna.Giuseppe.Progetto.Capstone.controllers;
import Averna.Giuseppe.Progetto.Capstone.entities.Product;
import Averna.Giuseppe.Progetto.Capstone.exceptions.ResourceNotFoundException;
import Averna.Giuseppe.Progetto.Capstone.repositories.CartRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {

    private final CartRepository cartRepository;

    public CartController(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @GetMapping("/cart")
    public List<Product> getCart() {
        return cartRepository.findAll();
    }

    @PostMapping("/cart")
    public Product addToCart(@RequestBody Product product) {
        return cartRepository.save(product);
    }

    @DeleteMapping("/cart/{id}")
    public List<Product> deleteFromCart(@PathVariable Long id) {
        cartRepository.findById(id)
                .map(product -> {
                    cartRepository.delete(product);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Prodotto con Id " + id + " non trovato nel carrello"));
        return cartRepository.findAll();
    }

}

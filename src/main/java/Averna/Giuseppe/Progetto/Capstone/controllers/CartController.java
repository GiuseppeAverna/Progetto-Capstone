package Averna.Giuseppe.Progetto.Capstone.controllers;

import Averna.Giuseppe.Progetto.Capstone.entities.Cart;
import Averna.Giuseppe.Progetto.Capstone.exceptions.ResourceNotFoundException;
import Averna.Giuseppe.Progetto.Capstone.repositories.CartRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
public class CartController {
    private final CartRepository cartRepository;

    public CartController(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @GetMapping("/carts")
    public List<Cart> getCarts() {
        return cartRepository.findAll();
    }

    @PostMapping("/carts")
    public List<Cart> addCarts(@RequestBody List<Cart> cart) {
        return cartRepository.saveAll(cart);
    }

    @GetMapping("/carts/{id}")
    public Cart getCart(@PathVariable Long id) {
        return cartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Carrello con Id " + id + " non trovato"));
    }

    @DeleteMapping("/carts/{id}")
    public ResponseEntity<?> deleteCart(@PathVariable Long id) {
        return cartRepository.findById(id)
                .map(product -> {
                    cartRepository.delete(product);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Carrello con Id " + id + " non trovato"));
    }

    @PutMapping("/carts/{id}")
    public Cart updateCart(@PathVariable Long id, @RequestBody Cart productRequest) {
        return cartRepository.findById(id)
                .map(cartRepository::save).orElseThrow(() -> new ResourceNotFoundException("Carrello con Id " + id + " non trovato"));
    }
}

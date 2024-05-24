package Averna.Giuseppe.Progetto.Capstone.controllers;

import Averna.Giuseppe.Progetto.Capstone.entities.Product;
import Averna.Giuseppe.Progetto.Capstone.entities.User;
import Averna.Giuseppe.Progetto.Capstone.exceptions.ResourceNotFoundException;
import Averna.Giuseppe.Progetto.Capstone.repositories.ProductRepository;
import Averna.Giuseppe.Progetto.Capstone.repositories.UserRepository;
import Averna.Giuseppe.Progetto.Capstone.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;



@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public UsersController(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    // @PreAuthorize("hasAuthority('ADMIN')") // PreAuthorize serve per poter dichiarare delle regole di accesso

    public Page<User> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(defaultValue = "id") String sortBy) {
        return this.usersService.getUsers(page, size, sortBy);
    }

    @GetMapping("/me")
    public User getProfile(@AuthenticationPrincipal User currentAuthenticatedUser){

        return currentAuthenticatedUser;
    }

    @PutMapping("/me")
    public User updateProfile(@AuthenticationPrincipal User currentAuthenticatedUser, @RequestBody User updatedUser){
        return this.usersService.findByIdAndUpdate(currentAuthenticatedUser.getId(), updatedUser);
    }

    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfile(@AuthenticationPrincipal User currentAuthenticatedUser){
        this.usersService.findByIdAndDelete(currentAuthenticatedUser.getId());
    }


    @GetMapping("/{userId}")
    public User findById(@PathVariable UUID userId){
        return this.usersService.findById(userId);
    }

    @PutMapping("/{userId}")

    public User findByIdAndUpdate(@PathVariable UUID userId, @RequestBody User body){
        return this.usersService.findByIdAndUpdate(userId, body);
    }

    @DeleteMapping("/{userId}")

    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID userId){
        this.usersService.findByIdAndDelete(userId);
    }

    @PutMapping("/{userId}/addToCart/{productId}")
    public User addToCart(@PathVariable UUID userId, @PathVariable Long productId) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

            user.getCart().add(product);
           return userRepository.save(user);
    }

    @PutMapping("/{userId}/removeFromCart/{productId}")
    public User removeFromCart(@PathVariable UUID userId, @PathVariable Long productId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        user.getCart().remove(product);
        return userRepository.save(user);
    }

    @PutMapping("/{userId}/clearCart")
    public User clearCart(@PathVariable UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        user.getCart().clear();
        return userRepository.save(user);
    }

}
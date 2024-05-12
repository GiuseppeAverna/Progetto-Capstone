package Averna.Giuseppe.Progetto.Capstone.controllers;

import Averna.Giuseppe.Progetto.Capstone.entities.User;
import Averna.Giuseppe.Progetto.Capstone.repositories.UserRepository;
import Averna.Giuseppe.Progetto.Capstone.services.AuthService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final AuthService authService;

    public AuthController(UserRepository userRepository, AuthService authService) {
        this.userRepository = userRepository;
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        // Implementa la logica di registrazione qui
        // Potrebbe includere la validazione dei dati dell'utente e l'hashing della password
        User newUser = authService.register(user);
        return ResponseEntity.ok(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        // Implementa la logica di login qui
        // Potrebbe includere la verifica delle credenziali dell'utente e la generazione di un token di sessione
        String token = authService.login(user);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody User user) {
        // Implementa la logica di logout qui
        // Potrebbe includere l'invalidazione del token di sessione dell'utente
        authService.logout(user);
        return ResponseEntity.ok().build();
    }
}

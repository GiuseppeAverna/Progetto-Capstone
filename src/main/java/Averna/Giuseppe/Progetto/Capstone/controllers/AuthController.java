package Averna.Giuseppe.Progetto.Capstone.controllers;

import Averna.Giuseppe.Progetto.Capstone.DTO.LoginDTO;
import Averna.Giuseppe.Progetto.Capstone.entities.User;
import Averna.Giuseppe.Progetto.Capstone.repositories.UserRepository;
//import Averna.Giuseppe.Progetto.Capstone.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;



@RestController
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginRequest) {
        // Ottieni l'utente dal repository utilizzando l'email fornita
        User user = userRepository.findByEmail(loginRequest.getEmail());

        if (user == null) {
            // Utente non trovato nel database, crea un nuovo utente
            user = new User();
            user.setEmail(loginRequest.getEmail());
            userRepository.save(user);
            return ResponseEntity.ok("Login successo!");
        } else {
            // Utente già presente nel database, restituisci un messaggio personalizzato
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Hai già effettuato l'accesso con questa email.");
        }

    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        // Verifica se l'utente è autenticato
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            // Effettua le operazioni di logout qui
            // Per semplicità, restituiamo semplicemente una risposta di successo
            return ResponseEntity.ok("Logout effettuato con successo!");
        } else {
            // Se l'utente non è autenticato, restituisci un errore di autorizzazione
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Utente non autorizzato a eseguire il logout.");
        }
    }
}

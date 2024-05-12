package Averna.Giuseppe.Progetto.Capstone.services;

import Averna.Giuseppe.Progetto.Capstone.entities.User;
import Averna.Giuseppe.Progetto.Capstone.repositories.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(User user) {
        // Hash della password dell'utente
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public String login(User user) {
        // Verifica le credenziali dell'utente e genera un token di sessione
        // Questo è solo un esempio e potrebbe non essere sicuro o adatto per un'applicazione del mondo reale
        User existingUser = userRepository.findByEmail(user.getUsername());
        if (existingUser != null && passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            return "token"; // Genera un vero token di sessione qui
        } else {
            throw new BadCredentialsException("Username o password non validi");
        }
    }

    public void logout(User user) {
        // Invalida il token di sessione dell'utente
        // Questo è solo un esempio e potrebbe non essere sicuro o adatto per un'applicazione del mondo reale
    }
}

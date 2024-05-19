package Averna.Giuseppe.Progetto.Capstone.services;

import Averna.Giuseppe.Progetto.Capstone.Payloads.UserLoginDTO;
import Averna.Giuseppe.Progetto.Capstone.entities.User;
import Averna.Giuseppe.Progetto.Capstone.exceptions.UnauthorizedException;
import Averna.Giuseppe.Progetto.Capstone.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UsersService usersService;
    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private PasswordEncoder bcrypt;

    public String authenticateUserAndGenerateToken(UserLoginDTO payload){
        // 1. Controllo le credenziali
        // 1.1 Cerco nel db tramite l'email l'utente
        User user = this.usersService.findByEmail(payload.email());
        // 1.2 Verifico se la password combacia con quella ricevuta nel payload
        if (bcrypt.matches(payload.password(), user.getPassword())) {
            // 2. Se è tutto OK, genero un token e lo torno
            return jwtTools.createToken(user);
        } else {
            // 3. Se le credenziali invece non fossero OK --> 401 (Unauthorized)
            throw new UnauthorizedException("Credenziali non valide! Effettua di nuovo il login!");
        }


    }
}
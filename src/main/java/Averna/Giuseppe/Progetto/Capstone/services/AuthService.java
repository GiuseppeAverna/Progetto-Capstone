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

        User user = this.usersService.findByEmail(payload.email());

        if (bcrypt.matches(payload.password(), user.getPassword())) {

            return jwtTools.createToken(user);
        } else {

            throw new UnauthorizedException("Credenziali non valide! Effettua di nuovo il login!");
        }


    }
}
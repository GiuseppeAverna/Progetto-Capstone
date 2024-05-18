package Averna.Giuseppe.Progetto.Capstone.services;

import Averna.Giuseppe.Progetto.Capstone.Payloads.UserLoginDTO;
import Averna.Giuseppe.Progetto.Capstone.entities.User;
import Averna.Giuseppe.Progetto.Capstone.exceptions.UnauthorizedException;
import Averna.Giuseppe.Progetto.Capstone.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
//public class AuthService {
//
//    @Autowired
//    private final UserRepository userRepository;
//    @Autowired
//    private final BCryptPasswordEncoder passwordEncoder;
//
//    public AuthService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    public User register(RegisterDTO registerDTO) {
//        User user = userRepository.findByEmail(registerDTO.email);
//        if (user != null) {
//            throw new UnprocessableEntityException("Email is already taken");
//        }
//        User createdUser = new User();
//        createdUser.email = registerDTO.email;
//        createdUser.username = registerDTO.username;
//        createdUser.password = registerDTO.password;
//        // createdUser.password = passwordEncoder.encode(registerDTO.password);
//        return userRepository.save(createdUser);
//    }
//
//    public User login(LoginDTO loginDTO) {
//        User user = userRepository.findByEmail(loginDTO.email);
//        if (user == null) {
//            throw new BadCredentialsException("User not found");
//        }
////        String encodedPassword = passwordEncoder.encode(loginDTO.password);
////        var isPasswordCorrect = passwordEncoder.matches(user.password, encodedPassword);
//        var isPasswordCorrect = user.password.equals(loginDTO.password);
//
//        if (!isPasswordCorrect) {
//            throw new BadCredentialsException("Incorrect password");
//        }
//        return user;
//    }
//}

@Service
public class AuthService {
    @Autowired
    private UsersService usersService;
    @Autowired
    private JWTTools jwtTools;

    public String authenticateUserAndGenerateToken(UserLoginDTO payload){
        // 1. Controllo le credenziali
        // 1.1 Cerco nel db tramite l'email l'utente
        User user = this.usersService.findByEmail(payload.email());
        // 1.2 Verifico se la password combacia con quella ricevuta nel payload
        if(user.getPassword().equals(payload.password())) {
            // 2. Se è tutto OK, genero un token e lo torno
            return jwtTools.createToken(user);
        } else {
            // 3. Se le credenziali invece non fossero OK --> 401 (Unauthorized)
            throw new UnauthorizedException("Credenziali non valide! Effettua di nuovo il login!");
        }


    }
}
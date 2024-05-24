package Averna.Giuseppe.Progetto.Capstone.controllers;

import Averna.Giuseppe.Progetto.Capstone.Payloads.NewUserDTO;
import Averna.Giuseppe.Progetto.Capstone.Payloads.NewUserRespDTO;
import Averna.Giuseppe.Progetto.Capstone.Payloads.UserLoginDTO;
import Averna.Giuseppe.Progetto.Capstone.Payloads.UserLoginResponseDTO;
import Averna.Giuseppe.Progetto.Capstone.exceptions.BadRequestException;
import Averna.Giuseppe.Progetto.Capstone.services.AuthService;
import Averna.Giuseppe.Progetto.Capstone.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private UsersService usersService;

    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO payload){
        return new UserLoginResponseDTO(this.authService.authenticateUserAndGenerateToken(payload));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUserRespDTO saveUser(@RequestBody @Validated NewUserDTO body, BindingResult validation){

        if(validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }

        return new NewUserRespDTO(this.usersService.save(body).getId());
    }

}
package Averna.Giuseppe.Progetto.Capstone.controllers;

import Averna.Giuseppe.Progetto.Capstone.DTO.NewUserDTO;
import Averna.Giuseppe.Progetto.Capstone.DTO.NewUserRespDTO;
import Averna.Giuseppe.Progetto.Capstone.entities.User;
import Averna.Giuseppe.Progetto.Capstone.exceptions.BadRequestException;
import Averna.Giuseppe.Progetto.Capstone.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping
    public Page<User> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(defaultValue = "id") String sortBy) {
        return this.usersService.getUsers(page, size, sortBy);
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

}

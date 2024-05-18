package Averna.Giuseppe.Progetto.Capstone.repositories;

import Averna.Giuseppe.Progetto.Capstone.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsersDAO extends JpaRepository<User, UUID> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);

}
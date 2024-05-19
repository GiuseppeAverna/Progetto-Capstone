
package Averna.Giuseppe.Progetto.Capstone.repositories;


import Averna.Giuseppe.Progetto.Capstone.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByEmail(String email);
}
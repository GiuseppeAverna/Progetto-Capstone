
package Averna.Giuseppe.Progetto.Capstone.repositories;


import Averna.Giuseppe.Progetto.Capstone.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
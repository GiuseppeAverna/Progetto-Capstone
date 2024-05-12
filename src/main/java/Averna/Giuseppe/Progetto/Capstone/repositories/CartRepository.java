package Averna.Giuseppe.Progetto.Capstone.repositories;
import Averna.Giuseppe.Progetto.Capstone.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Product, Long> {
}

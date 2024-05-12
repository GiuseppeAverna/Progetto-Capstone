package Averna.Giuseppe.Progetto.Capstone.repositories;
import Averna.Giuseppe.Progetto.Capstone.entities.Cart;
import Averna.Giuseppe.Progetto.Capstone.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}

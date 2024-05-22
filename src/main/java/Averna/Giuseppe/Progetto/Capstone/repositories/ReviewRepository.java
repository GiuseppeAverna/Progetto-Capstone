package Averna.Giuseppe.Progetto.Capstone.repositories;

import Averna.Giuseppe.Progetto.Capstone.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByProductId(Long productId);
}

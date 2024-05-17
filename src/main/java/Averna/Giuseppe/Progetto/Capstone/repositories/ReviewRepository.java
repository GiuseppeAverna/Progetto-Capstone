package Averna.Giuseppe.Progetto.Capstone.repositories;

import Averna.Giuseppe.Progetto.Capstone.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReviewRepository extends JpaRepository<Review, Long> {
    }


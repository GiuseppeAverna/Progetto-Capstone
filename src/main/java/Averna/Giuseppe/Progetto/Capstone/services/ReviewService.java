package Averna.Giuseppe.Progetto.Capstone.services;

import Averna.Giuseppe.Progetto.Capstone.entities.Review;
import Averna.Giuseppe.Progetto.Capstone.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> getReviewsByProductId(Long productId) {
        return reviewRepository.findByProductId(productId);
    }
}

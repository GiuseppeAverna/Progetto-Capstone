package Averna.Giuseppe.Progetto.Capstone.controllers;
import Averna.Giuseppe.Progetto.Capstone.entities.Review;
import Averna.Giuseppe.Progetto.Capstone.exceptions.ResourceNotFoundException;
import Averna.Giuseppe.Progetto.Capstone.repositories.ReviewRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
public class ReviewController {
    private final ReviewRepository reviewRepository;

    public ReviewController(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @GetMapping("/reviews")
    public List<Review> getReviews() {
        return reviewRepository.findAll();
    }

    @PostMapping("/reviews")
    public Review addReview(@RequestBody Review review) {
        return reviewRepository.save(review);
    }

    @GetMapping("/reviews/{id}")
    public Review getReview(@PathVariable Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recensione con Id " + id + " non trovata"));
    }

    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable Long id) {
        return reviewRepository.findById(id)
                .map(review -> {
                    reviewRepository.delete(review);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Recensione con Id " + id + " non trovata"));
    }

    @PutMapping("/reviews/{id}")
    public Review updateReview(@PathVariable Long id, @RequestBody Review reviewRequest) {
        return reviewRepository.findById(id)
                .map(review -> {
                    review.setRating(reviewRequest.getRating());
                    review.setReviewText(reviewRequest.getReviewText());
                    return reviewRepository.save(review);
                }).orElseThrow(() -> new ResourceNotFoundException("Recensione con Id " + id + " non trovata"));
    }
}

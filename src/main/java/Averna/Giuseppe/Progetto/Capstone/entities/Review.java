package Averna.Giuseppe.Progetto.Capstone.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;
    private String productName;
    private int rating;
    private String reviewText;

    public Review(Long productId, String productName, int rating, String reviewText) {
        this.productId = productId;
        this.productName = productName;
        this.rating = rating;
        this.reviewText = reviewText;
    }
}

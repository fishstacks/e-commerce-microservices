package com.neon.neonspecs.review;

import com.neon.neonspecs.product.Product;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(Long reviewId);

    void addReview(Long productId, Review review);

    Review getReviewById(Long reviewId);
    boolean deleteById(Long reviewId);
    boolean updateReview(Long id, Review updatedReview);
}

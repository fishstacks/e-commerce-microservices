package com.neonspecs.reviewms.review;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewService {

    boolean addReview(Long productId, Review review);

    Review getReviewById(Long reviewId);

    List<Review> getReviewsByProductId(Long productId, Pageable pageable);
    boolean deleteById(Long reviewId);
    boolean updateReview(Long id, Review updatedReview);
}

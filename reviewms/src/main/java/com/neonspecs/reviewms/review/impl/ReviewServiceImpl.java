package com.neonspecs.reviewms.review.impl;

import com.neonspecs.reviewms.review.Review;
import com.neonspecs.reviewms.review.ReviewRepository;
import com.neonspecs.reviewms.review.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }



    @Override
    public boolean addReview(Long productId, Review review){
        if (productId != null && review != null){
            review.setProductId(productId);
            reviewRepository.save(review);
            return true;
        }
        else {
            return false;
        }


    }

    @Override
    public Review getReviewById(Long reviewId){
        return reviewRepository.findById(reviewId).orElse(null);
    }

    @Override
    public boolean deleteById(Long reviewId) {
        try {
            reviewRepository.deleteById(reviewId);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Review> getReviewsByProductId(Long productId, Pageable pageable) {
        Page<Review> reviewPage = reviewRepository.findByProductId(productId, pageable);
        return reviewPage.getContent();
    }

    @Override
    public boolean updateReview(Long id, Review updatedReview){
        Optional<Review> reviewOptional = reviewRepository.findById(id);
        if (reviewOptional.isPresent()){
            Review review = reviewOptional.get();
            review.setTitle(updatedReview.getTitle());
            review.setDescription(updatedReview.getDescription());
            review.setRating(updatedReview.getRating());
            reviewRepository.save(review);
            return true;
        }
        return false;

    }
}

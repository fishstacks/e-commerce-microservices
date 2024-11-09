package com.neon.neonspecs.review.impl;

import com.neon.neonspecs.product.Product;
import com.neon.neonspecs.product.ProductService;
import com.neon.neonspecs.review.Review;
import com.neon.neonspecs.review.ReviewRepository;
import com.neon.neonspecs.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductService productService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, ProductService productService) {
        this.reviewRepository = reviewRepository;
        this.productService = productService;
    }

    @Override
    public List<Review> getAllReviews(Long productId){
        List<Review> reviews = reviewRepository.getProductById(productId);
        return reviews;
    }

    @Override
    public void addReview(Long productId, Review review){
        Product product = productService.getProductById(productId);
        if (product != null){
            review.setProduct(product);
            reviewRepository.save(review);
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

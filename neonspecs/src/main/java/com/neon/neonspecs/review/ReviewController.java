package com.neon.neonspecs.review;

import com.neon.neonspecs.product.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products/{productId}")
public class ReviewController {
    private ReviewService reviewService;


    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long productId){
        return new ResponseEntity<>(reviewService.getAllReviews(productId), HttpStatus.OK);

    }

    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@PathVariable Long productId, @RequestBody Review review){
        reviewService.addReview(productId, review);
        return new ResponseEntity<>("Review added!", HttpStatus.OK);
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long reviewId){
        Review review= reviewService.getReviewById(reviewId);
        if (review != null)
            return new ResponseEntity<>(review, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable Long id){
        boolean deleted = reviewService.deleteById(id);
        if (deleted)
            return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody Review updatedReview){
        boolean updated = reviewService.updateReview(id, updatedReview);
        if (updated)
            return new ResponseEntity<>("Review updated", HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}

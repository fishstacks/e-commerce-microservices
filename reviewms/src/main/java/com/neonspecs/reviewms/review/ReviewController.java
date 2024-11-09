package com.neonspecs.reviewms.review;

import com.neonspecs.reviewms.review.messaging.ReviewMessageProducer;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private ReviewService reviewService;
    private ReviewMessageProducer reviewMessageProducer;


    public ReviewController(ReviewService reviewService, ReviewMessageProducer reviewMessageProducer) {
        this.reviewService = reviewService;
        this.reviewMessageProducer = reviewMessageProducer;
    }


    @PostMapping
    public ResponseEntity<String> addReview(@RequestParam Long productId, @RequestBody Review review){
        boolean isReviewSaved = reviewService.addReview(productId, review);
        if (isReviewSaved){
            reviewMessageProducer.sendMessage(review);
            return new ResponseEntity<>("Review added!", HttpStatus.OK);}
        else
            return new ResponseEntity<>("Review not saved", HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Review>> getReviewsByProductId(@RequestParam("productId") Long productId,
                                              @RequestParam(value = "limit", required = false) Integer limit) {
        Pageable pageable = (limit != null) ? PageRequest.of(0, limit) : Pageable.unpaged();
        return new ResponseEntity<>(reviewService.getReviewsByProductId(productId, pageable), HttpStatus.OK);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long reviewId){
        Review review= reviewService.getReviewById(reviewId);
        if (review != null)
            return new ResponseEntity<>(review, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId){
        boolean deleted = reviewService.deleteById(reviewId);
        if (deleted)
            return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId, @RequestBody Review updatedReview){
        boolean updated = reviewService.updateReview(reviewId, updatedReview);
        if (updated)
            return new ResponseEntity<>("Review updated", HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/averageRating")
    public Double getAverageRating(@RequestParam Long productId){
        List<Review> reviewList = reviewService.getReviewsByProductId(productId, Pageable.unpaged());
        return reviewList.stream().mapToDouble(Review::getRating).average().orElse(0.0);

    }

}

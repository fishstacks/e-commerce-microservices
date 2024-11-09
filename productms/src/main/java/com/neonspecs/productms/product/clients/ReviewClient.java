package com.neonspecs.productms.product.clients;

import com.neonspecs.productms.product.external.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(name = "REVIEWMS")
public interface ReviewClient {
    @GetMapping("/reviews")
    List<Review> getReviewOverview(@RequestParam("productId") Long id,
                                      @RequestParam(value = "limit") int limit);


    @GetMapping("/averageRating")
    Double averageRating(@RequestParam Long productId);




}

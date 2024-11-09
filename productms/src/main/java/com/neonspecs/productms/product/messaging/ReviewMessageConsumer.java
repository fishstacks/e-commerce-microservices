package com.neonspecs.productms.product.messaging;

import com.neonspecs.productms.product.dto.ReviewMessage;
import com.neonspecs.productms.product.ProductService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ReviewMessageConsumer {

    private final ProductService productService;

    public ReviewMessageConsumer(ProductService productService) {
        this.productService = productService;
    }

    @RabbitListener(queues = "productRatingQueue")
    public void consumeMessage(ReviewMessage reviewMessage){
        productService.updateProductRating(reviewMessage);

    }
}

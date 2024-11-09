package com.neonspecs.productms.product.impl;

import com.neonspecs.productms.product.dto.ReviewMessage;
import com.neonspecs.productms.product.ProductDTO;
import com.neonspecs.productms.product.clients.InventoryClient;
import com.neonspecs.productms.product.clients.ReviewClient;
import com.neonspecs.productms.product.dto.ProductMapper;
import com.neonspecs.productms.product.external.InventoryStatusDTO;
import com.neonspecs.productms.product.Product;
import com.neonspecs.productms.product.ProductRepository;
import com.neonspecs.productms.product.ProductService;
import com.neonspecs.productms.product.external.Review;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;


    private InventoryClient inventoryClient;
    private ReviewClient reviewClient;


    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, InventoryClient inventoryClient, ReviewClient reviewClient) {
        this.productRepository = productRepository;
        this.inventoryClient = inventoryClient;
        this.reviewClient = reviewClient;
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll() ;
    }

    @Override
    public void createProduct(Product product) {
        productRepository.save(product);

    }



    @Override
    public boolean deleteById(Long id) {
        try {
            productRepository.deleteById(id);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateProduct(Long id, Product updatedProduct){
        Optional<Product> productOptional = productRepository.findById(id);
            if (productOptional.isPresent()){
                Product product = productOptional.get();
                product.setProduct_name(updatedProduct.getProduct_name());
                product.setDescription(updatedProduct.getDescription());
                product.setCategory_id(updatedProduct.getCategory_id());
                product.setPrice(updatedProduct.getPrice());
                product.setImageUrl(updatedProduct.getImageUrl());
                product.setManufacturer(updatedProduct.getManufacturer());
                product.setSku(updatedProduct.getSku());
                productRepository.save(product);
                return true;
            }
        return false;

    }

    @Override
    public void updateProductRating(ReviewMessage reviewMessage) {
        Product product = productRepository.findById(reviewMessage.getProductId()).orElseThrow(() -> new NotFoundException("not fouuuund") );
        Double averageRating = reviewClient.averageRating(reviewMessage.getProductId());
        product.setRating(averageRating);
        productRepository.save(product);



    }


    private List<InventoryStatusDTO> getAvailabilityBySku(String sku) {
        List<InventoryStatusDTO> availability = inventoryClient.getAvailabilityBySku(sku);
        return availability;
    }



    private List<Review> getReviewOverview(Long product_id){

        List<Review> reviewOverview = reviewClient.getReviewOverview(product_id,2);
        return reviewOverview;

    }




    @Override
    @CircuitBreaker(name = "inventoryBreaker", fallbackMethod = "inventoryBreakerFallback")
    @RateLimiter(name = "inventoryRateLimiter", fallbackMethod = "inventoryRateLimiterFallback")
    public Optional<ProductDTO> getProductById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        List<InventoryStatusDTO> inventoryStatus = getAvailabilityBySku(product.getSku());
        Optional<ProductDTO> productDTO = Optional.of(ProductMapper.toProductDTO(product, inventoryStatus));
        productDTO.ifPresent(dto -> dto.setReviews(getReviewOverview(id)));
        return productDTO;

    }

    private Optional<ProductDTO> inventoryBreakerFallback(Long id, Exception e){

        Optional<ProductDTO> fallbackInventory = Optional.of(new ProductDTO("Live availability unavailable"));

        return fallbackInventory;
    }

    private Optional<ProductDTO> inventoryRateLimiterFallback(Long id, Exception e){

        Optional<ProductDTO> fallbackInventory = Optional.of(new ProductDTO("Rate limit exceeded"));

        return fallbackInventory;
    }



}


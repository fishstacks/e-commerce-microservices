package com.neonspecs.productms.product;

import com.neonspecs.productms.product.dto.ReviewMessage;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();
    void createProduct(Product product);
    Optional<ProductDTO> getProductById(Long id);
    boolean deleteById(Long id);
    boolean updateProduct(Long id, Product updatedProduct);

    void updateProductRating(ReviewMessage reviewMessage);
}

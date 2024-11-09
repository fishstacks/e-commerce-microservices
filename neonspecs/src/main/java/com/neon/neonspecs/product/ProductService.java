package com.neon.neonspecs.product;

import com.neon.neonspecs.inventoryitem.InventoryStatusDTO;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    void createProduct(Product product);
    Product getProductById(Long id);
    boolean deleteById(Long id);
    boolean updateProduct(Long id, Product updatedProduct);
    List<InventoryStatusDTO> getAvailabilityBySku(String sku);
}

package com.neon.neonspecs.product.impl;

import com.neon.neonspecs.inventoryitem.InventoryItemService;
import com.neon.neonspecs.inventoryitem.InventoryStatusDTO;
import com.neon.neonspecs.product.Product;
import com.neon.neonspecs.product.ProductRepository;
import com.neon.neonspecs.product.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    //private List<Product> products = new ArrayList<>();

    ProductRepository productRepository;
    private InventoryItemService inventoryItemService;

    public ProductServiceImpl(ProductRepository productRepository, InventoryItemService inventoryItemService) {
        this.productRepository = productRepository;
        this.inventoryItemService = inventoryItemService;
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
    public Product getProductById(Long id) {
       return productRepository.findById(id).orElse(null);
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
    public List<InventoryStatusDTO> getAvailabilityBySku(String sku) {
        return inventoryItemService.getAvailabilityBySku(sku);
    }


}


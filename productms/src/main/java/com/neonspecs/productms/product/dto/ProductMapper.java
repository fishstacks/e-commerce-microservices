package com.neonspecs.productms.product.dto;

import com.neonspecs.productms.product.external.InventoryStatusDTO;
import com.neonspecs.productms.product.Product;
import com.neonspecs.productms.product.ProductDTO;

import java.util.List;

public class ProductMapper {
    public static ProductDTO toProductDTO(Product product, List<InventoryStatusDTO> inventoryStatus) {
        ProductDTO dto = new ProductDTO();
        dto.setProduct_id(product.getProduct_id());
        dto.setProduct_name(product.getProduct_name());
        dto.setSku(product.getSku());
        dto.setDescription(product.getDescription());
        dto.setRating(product.getRating());
        dto.setPrice(product.getPrice());
        dto.setCategory_id(product.getCategory_id());
        dto.setManufacturer(product.getManufacturer());
        dto.setImageUrl(product.getImageUrl());
        dto.setAvailability(inventoryStatus);
        return dto;
    }
}

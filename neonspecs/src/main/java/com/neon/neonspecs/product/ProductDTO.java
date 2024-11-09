package com.neon.neonspecs.product;

import com.neon.neonspecs.inventoryitem.InventoryStatusDTO;

import java.util.List;

public class ProductDTO {
    Product product;
    List<InventoryStatusDTO> availability;

    public ProductDTO(Product product, List<InventoryStatusDTO> availability) {
        this.product = product;
        this.availability = availability;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<InventoryStatusDTO> getAvailability() {
        return availability;
    }

    public void setAvailability(List<InventoryStatusDTO> availability) {
        this.availability = availability;
    }
}

package com.neon.neonspecs.inventoryitem;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.neon.neonspecs.product.Product;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "inventory_table")
public class InventoryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventory_item_id;
    private String shop_name;

    private String sku;
    private int quantity;



    public InventoryItem() {
    }

    public Long getInventory_item_id() {
        return inventory_item_id;
    }

    public void setInventory_item_id(Long inventory_item_id) {
        this.inventory_item_id = inventory_item_id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }
}

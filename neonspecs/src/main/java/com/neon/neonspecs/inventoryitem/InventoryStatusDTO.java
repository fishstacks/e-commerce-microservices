package com.neon.neonspecs.inventoryitem;


public class InventoryStatusDTO {
    private String shopName;
    private InventoryStatus status;

    public InventoryStatusDTO(String shopName, InventoryStatus status) {
        this.shopName = shopName;
        this.status = status;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopId(String shopName) {
        this.shopName = shopName;
    }

    public InventoryStatus getStatus() {
        return status;
    }

    public void setStatus(InventoryStatus status) {
        this.status = status;
    }
}


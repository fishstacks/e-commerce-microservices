package com.neonspecs.inventoryms.inventoryitem;

import java.util.List;

public interface InventoryItemService {
    List<InventoryItem> findAll();

    void createInventoryItem(InventoryItem inventoryItem);

    InventoryItem getInventoryItemById(Long id);

    boolean deleteById(Long id);

    boolean updateInventoryItem(Long id, InventoryItem updatedInventoryItem);

    List<InventoryItem> getInventoryItemsBySku(String sku);

    List<InventoryStatusDTO> getAvailabilityBySku(String sku);
}

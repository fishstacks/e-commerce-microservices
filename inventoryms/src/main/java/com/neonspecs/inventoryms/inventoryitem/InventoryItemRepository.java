package com.neonspecs.inventoryms.inventoryitem;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
    List<InventoryItem> findAllBySku(String sku);

}

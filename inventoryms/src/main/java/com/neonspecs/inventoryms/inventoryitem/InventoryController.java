package com.neonspecs.inventoryms.inventoryitem;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory-records/admin")
public class InventoryController {
    private InventoryItemService inventoryItemService;


    public InventoryController(InventoryItemService inventoryItemService) {
        this.inventoryItemService = inventoryItemService;
    }

    @GetMapping
    public ResponseEntity<List<InventoryItem>> findAll(){
        return ResponseEntity.ok(inventoryItemService.findAll());
    }

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody InventoryItem inventoryItem){
        inventoryItemService.createInventoryItem(inventoryItem);
        return new ResponseEntity<>("Inventory item added!", HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<InventoryItem> getInventoryById(@PathVariable Long id){
        InventoryItem inventoryItem = inventoryItemService.getInventoryItemById(id);
        if (inventoryItem != null)
            return new ResponseEntity<>(inventoryItem, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInventory(@PathVariable Long id){
        boolean deleted = inventoryItemService.deleteById(id);
        if (deleted)
            return new ResponseEntity<>("Inventory item deleted successfully", HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateInventory(@PathVariable Long id, @RequestBody InventoryItem updatedInventoryItem){
        boolean updated = inventoryItemService.updateInventoryItem(id, updatedInventoryItem);
        if (updated)
            return new ResponseEntity<>("Inventory item updated", HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/availability/{sku}")
    public ResponseEntity<List<InventoryStatusDTO>> getAvailabilityBySku(@PathVariable String sku) {
        List<InventoryStatusDTO> availability = inventoryItemService.getAvailabilityBySku(sku);
        return new ResponseEntity<>(availability, HttpStatus.OK);
    }
}

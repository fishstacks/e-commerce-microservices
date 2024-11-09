package com.neon.neonspecs.inventoryitem.impl;

import com.neon.neonspecs.inventoryitem.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventoryItemServiceImpl implements InventoryItemService {
    private InventoryItemRepository inventoryItemRepository;

    private InventoryStatusDTO saveInventoryStatus(InventoryItem inventoryitem) {
        InventoryStatus status;

        if (inventoryitem.getQuantity() == 0) {
            status = InventoryStatus.UNAVAILABLE;
        } else if (inventoryitem.getQuantity() <= 5) {
            status = InventoryStatus.FEW_ITEMS_LEFT;
        } else {
            status = InventoryStatus.AVAILABLE;
        }

        return new InventoryStatusDTO(inventoryitem.getShop_name(), status);
    }

    public InventoryItemServiceImpl(InventoryItemRepository inventoryItemRepository) {
        this.inventoryItemRepository = inventoryItemRepository;
    }
    
    @Override
    public List<InventoryItem> findAll(){
        return this.inventoryItemRepository.findAll();
    }
    
    @Override
    public void createInventoryItem(InventoryItem inventoryItem){
        this.inventoryItemRepository.save(inventoryItem);
    }

    @Override
    public InventoryItem getInventoryItemById(Long id){
        return this.inventoryItemRepository.findById(id).orElse(null);
    }
    
    @Override
    public boolean deleteById(Long id){
        try {
            inventoryItemRepository.deleteById(id);
            return true;

        } catch (Exception e) {
            return false;
        }
        
    }
    
    @Override
    public boolean updateInventoryItem(Long id, InventoryItem updatedInventoryItem){
        Optional<InventoryItem> inventoryOptional = inventoryItemRepository.findById(id);
        if (inventoryOptional.isPresent()){
            InventoryItem inventoryItem = inventoryOptional.get();
            inventoryItem.setShop_name(updatedInventoryItem.getShop_name());
            inventoryItem.setSku(updatedInventoryItem.getSku());
            inventoryItem.setQuantity(updatedInventoryItem.getQuantity());
            inventoryItemRepository.save(inventoryItem);

            return true;
        }
        return false;
    }
    
    @Override
    public List<InventoryItem> getInventoryItemsBySku(String sku){
        return this.inventoryItemRepository.findAllBySku(sku);
    }

    @Override
    public List<InventoryStatusDTO> getAvailabilityBySku(String sku) {
        List<InventoryItem> inventoryList = inventoryItemRepository.findAllBySku(sku);

        return inventoryList.stream()
                .map(inventoryitem -> this.saveInventoryStatus(inventoryitem))
                .collect(Collectors.toList());
    }
}

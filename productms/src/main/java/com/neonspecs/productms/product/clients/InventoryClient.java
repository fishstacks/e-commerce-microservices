package com.neonspecs.productms.product.clients;


import com.neonspecs.productms.product.external.InventoryStatusDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "INVENTORYMS")
public interface InventoryClient {
    @GetMapping("/inventory-records/admin/availability/{sku}")
    List<InventoryStatusDTO> getAvailabilityBySku(@PathVariable String sku);
}

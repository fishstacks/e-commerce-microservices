package com.neon.neonspecs.manufacturer;

import com.neon.neonspecs.product.Product;

import java.util.List;

public interface ManufacturerService {
    Manufacturer  getManufacturerById(Long id);
    List<Manufacturer> findAll();
    void createManufacturer(Manufacturer manufacturer);
    boolean deleteById(Long id);
    boolean updateManufacturer(Long id, Manufacturer updatedManufacturer);
}
package com.neon.neonspecs.manufacturer.impl;

import com.neon.neonspecs.manufacturer.Manufacturer;
import com.neon.neonspecs.manufacturer.ManufacturerRespository;
import com.neon.neonspecs.manufacturer.ManufacturerService;
import com.neon.neonspecs.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private ManufacturerRespository manufacturerRepository;

    private ManufacturerServiceImpl(ManufacturerRespository manufacturerRepository){
        this.manufacturerRepository=manufacturerRepository;
    }

    @Override
    public Manufacturer getManufacturerById(Long id){
        return this.manufacturerRepository.findById(id).orElse(null);
    }

    @Override
    public List<Manufacturer> findAll(){
        return this.manufacturerRepository.findAll();
    }

    @Override
    public void createManufacturer(Manufacturer manufacturer){
        this.manufacturerRepository.save(manufacturer);

    }
    @Override
    public boolean deleteById(Long id){
        try {
            manufacturerRepository.deleteById(id);
            return true;

        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean updateManufacturer(Long id, Manufacturer updatedManufacturer){
        Optional<Manufacturer> manufacturerOptional = manufacturerRepository.findById(id);
        if (manufacturerOptional.isPresent()){
            Manufacturer manufacturer = manufacturerOptional.get();
            manufacturer.setManufacturer_name(updatedManufacturer.getManufacturer_name());
            manufacturer.setManufacturer_logo(updatedManufacturer.getManufacturer_name());

            return true;
        }
        return false;



    }


}

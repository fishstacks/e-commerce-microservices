package com.neon.neonspecs.manufacturer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
public class ManufacturerController {
        private ManufacturerService manufacturerService;


        public ManufacturerController(ManufacturerService manufacturerService) {
            this.manufacturerService = manufacturerService;
        }

        @GetMapping
        public ResponseEntity<List<Manufacturer>> findAll(){
            return ResponseEntity.ok(manufacturerService.findAll());
        }

        @PostMapping("/admin")
        public ResponseEntity<String> createProduct(@RequestBody Manufacturer manufacturer){
            manufacturerService.createManufacturer(manufacturer);
            return new ResponseEntity<>("Manufacturer added!", HttpStatus.OK);
        }
        @GetMapping("/{id}")
        public ResponseEntity<Manufacturer> getProductById(@PathVariable Long id){
            Manufacturer product= manufacturerService.getManufacturerById(id);
            if (product != null)
                return new ResponseEntity<>(product, HttpStatus.OK);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        @DeleteMapping("/{id}/admin")
        public ResponseEntity<String> deleteManufacturer(@PathVariable Long id){
            boolean deleted = manufacturerService.deleteById(id);
            if (deleted)
                return new ResponseEntity<>("Manufacturer deleted successfully", HttpStatus.OK);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        @PutMapping("/{id}/admin")
        public ResponseEntity<String> updateManufacturer(@PathVariable Long id, @RequestBody Manufacturer updatedManufacturer){
            boolean updated = manufacturerService.updateManufacturer(id, updatedManufacturer);
            if (updated)
                return new ResponseEntity<>("Manufacturer updated", HttpStatus.OK);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
}

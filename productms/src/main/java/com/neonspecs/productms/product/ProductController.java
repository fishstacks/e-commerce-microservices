package com.neonspecs.productms.product;

import com.neonspecs.productms.product.external.InventoryStatusDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll(){
        return ResponseEntity.ok(productService.findAll());
    }

    @PostMapping("/admin")
    public ResponseEntity<String> createProduct(@RequestBody Product product){
        productService.createProduct(product);
        return new ResponseEntity<>("Product added!", HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(productDTO -> new ResponseEntity<>(productDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }



    @DeleteMapping("/{id}/admin")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        boolean deleted = productService.deleteById(id);
        if (deleted)
            return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PutMapping("/{id}/admin")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct){
        boolean updated = productService.updateProduct(id, updatedProduct);
        if (updated)
            return new ResponseEntity<>("Product updated", HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}

package com.neonspecs.productms.manufacturer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.neonspecs.productms.product.Product;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "manufacturer_table")
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long manufacturer_id;
    private String manufacturer_name;
    private String manufacturer_logo;

    @JsonIgnore
    @OneToMany(mappedBy = "manufacturer")
    private List<Product> products;

    public Long getManufacturer_id() {
        return manufacturer_id;
    }

    public String getManufacturer_name() {
        return manufacturer_name;
    }

    public String getManufacturer_logo() {
        return manufacturer_logo;
    }

    public void setManufacturer_id(Long manufacturer_id) {
        this.manufacturer_id = manufacturer_id;
    }

    public void setManufacturer_name(String manufacturer_name) {
        this.manufacturer_name = manufacturer_name;
    }

    public void setManufacturer_logo(String manufacturer_logo) {
        this.manufacturer_logo = manufacturer_logo;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }



}

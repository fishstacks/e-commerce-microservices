package com.neon.neonspecs.product;

import com.neon.neonspecs.manufacturer.Manufacturer;
import com.neon.neonspecs.review.Review;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "product_table")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;
    private String product_name;
    private String description;
    private BigDecimal price;
    @ManyToOne
    private Manufacturer manufacturer;

    @OneToMany(mappedBy = "product")
    private List<Review> reviews;

    private Long category_id;
    private String sku;
    private String imageUrl;

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }
    public Product() {
    }

    public Product(Long product_id, String product_name, String description, BigDecimal price, Manufacturer manufacturer, Long category_id, String sku, String imageUrl) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.description = description;
        this.price = price;
        this.manufacturer = manufacturer;
        this.category_id = category_id;
        this.sku = sku;
        this.imageUrl = imageUrl;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Long getCategory_id() {
        return category_id;
    }

    public String getSku() {
        return sku;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

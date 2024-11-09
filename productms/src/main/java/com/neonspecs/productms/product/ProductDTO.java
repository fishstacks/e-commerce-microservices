package com.neonspecs.productms.product;

import com.neonspecs.productms.manufacturer.Manufacturer;
import com.neonspecs.productms.product.external.InventoryStatusDTO;
import com.neonspecs.productms.product.external.Review;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.util.List;

public class ProductDTO {
    private Long product_id;
    private String product_name;
    private String description;
    private BigDecimal price;

    private Manufacturer manufacturer;

    private Double rating;
    private List<Review> reviews;

    private Long category_id;
    private String sku;
    private String imageUrl;
    List<InventoryStatusDTO> availability;

    public ProductDTO(String description) {
        this.description = description;
    }

    public ProductDTO() {
    }

    public ProductDTO(Long product_id, String product_name, String description, BigDecimal price, Manufacturer manufacturer, Long category_id, String sku, String imageUrl, List<InventoryStatusDTO> availability) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.description = description;
        this.price = price;
        this.manufacturer = manufacturer;
        this.category_id = category_id;
        this.sku = sku;
        this.imageUrl = imageUrl;
        this.availability = availability;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<InventoryStatusDTO> getAvailability() {
        return availability;
    }

    public void setAvailability(List<InventoryStatusDTO> availability) {
        this.availability = availability;
    }
}

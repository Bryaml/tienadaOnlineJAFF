package com.jaff.tiendaOnline.Entity;

import jakarta.persistence.*;

@Entity
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @Lob
    @Column(name = "image", columnDefinition = "BLOB")
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    // Getters and Setters
    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

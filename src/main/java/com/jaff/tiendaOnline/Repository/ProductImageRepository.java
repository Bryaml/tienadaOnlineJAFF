package com.jaff.tiendaOnline.Repository;

import com.jaff.tiendaOnline.Entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
}
package com.jaff.tiendaOnline.Repository;

import com.jaff.tiendaOnline.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(String category);
    List<Product> findByCategoryAndSubcategory(String category, String subcategory);

    @Query("SELECT DISTINCT p.subcategory FROM Product p WHERE p.category = :category")
    List<String> findDistinctSubcategoriesByCategory(String category);
}

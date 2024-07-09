package com.jaff.tiendaOnline.Service.Product;

import com.jaff.tiendaOnline.Entity.Product;
import com.jaff.tiendaOnline.Entity.ProductImage;
import com.jaff.tiendaOnline.Repository.ProductImageRepository;
import com.jaff.tiendaOnline.Repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public Product addProduct(Product product, List<String> imagePaths) {
        for (String imagePath : imagePaths) {
            ProductImage productImage = new ProductImage();
            productImage.setImagePath(imagePath);
            productImage.setProduct(product);
            product.getImages().add(productImage);
        }

        return productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public List<String> findDistinctSubcategoriesByCategory(String category) {
        return productRepository.findDistinctSubcategoriesByCategory(category);
    }

    public List<Product> getProductsByCategoryAndSubcategory(String category, String subcategory) {
        return productRepository.findByCategoryAndSubcategory(category, subcategory);
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }
}

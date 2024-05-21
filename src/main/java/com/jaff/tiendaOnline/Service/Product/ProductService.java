package com.jaff.tiendaOnline.Service.Product;

import com.jaff.tiendaOnline.Entity.Product;
import com.jaff.tiendaOnline.Entity.ProductImage;
import com.jaff.tiendaOnline.Repository.ProductImageRepository;
import com.jaff.tiendaOnline.Repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductImageRepository productImageRepository) {
        this.productRepository = productRepository;
        this.productImageRepository = productImageRepository;
    }

    @Transactional
    public Product addProduct(Product product, List<byte[]> images) {
        product = productRepository.save(product);

        for (byte[] image : images) {
            ProductImage productImage = new ProductImage();
            productImage.setProduct(product);
            productImage.setImage(image);
            productImageRepository.save(productImage);
        }

        return product;
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
}

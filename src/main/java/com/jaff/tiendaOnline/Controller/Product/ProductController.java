package com.jaff.tiendaOnline.Controller.Product;

import com.jaff.tiendaOnline.Entity.Product;
import com.jaff.tiendaOnline.Service.Product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestParam String name,
                                              @RequestParam String description,
                                              @RequestParam int stock,
                                              @RequestParam double price,
                                              @RequestParam String category,
                                              @RequestParam String subcategory,
                                              @RequestParam("images") List<MultipartFile> images) {
        try {
            Product product = new Product();
            product.setName(name);
            product.setDescription(description);
            product.setStock(stock);
            product.setPrice(price);
            product.setCategory(category);
            product.setSubcategory(subcategory);

            List<byte[]> imageBytes = new ArrayList<>();
            for (MultipartFile image : images) {
                imageBytes.add(image.getBytes());
            }

            Product createdProduct = productService.addProduct(product, imageBytes);
            return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }





    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category) {
        List<Product> products = productService.getProductsByCategory(category);
        return ResponseEntity.ok(products);
    }
    @GetMapping("/categories/{category}/subcategories")
    public List<String> getDistinctSubcategoriesByCategory(@PathVariable String category) {
        return productService.findDistinctSubcategoriesByCategory(category);
    }
}

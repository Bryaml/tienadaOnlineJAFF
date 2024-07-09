package com.jaff.tiendaOnline.Controller.Product;

import com.jaff.tiendaOnline.Entity.Product;
import com.jaff.tiendaOnline.Service.Product.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
/*el boo */
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<?> createProduct(HttpServletRequest request,
                                           @RequestParam(required = false) List<MultipartFile> images) {
        try {
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            int stock = Integer.parseInt(request.getParameter("stock"));
            double price = Double.parseDouble(request.getParameter("price"));
            String category = request.getParameter("category");
            String subcategory = request.getParameter("subcategory");

            Product product = new Product();
            product.setName(name);
            product.setDescription(description);
            product.setStock(stock);
            product.setPrice(price);
            product.setCategory(category);
            product.setSubcategory(subcategory);

            List<String> imagePaths = new ArrayList<>();
            if (images != null && !images.isEmpty()) {
                for (MultipartFile image : images) {
                    String imagePath = saveImage(image);
                    imagePaths.add(imagePath);
                }
            }

            Product savedProduct = productService.addProduct(product, imagePaths);
            return ResponseEntity.ok(savedProduct);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el producto.");
        }
    }

    private String saveImage(MultipartFile image) throws IOException {
        String uploadDir = "uploads";
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String originalFileName = StringUtils.cleanPath(image.getOriginalFilename());
        String uniqueFileName = System.currentTimeMillis() + "_" + originalFileName;
        Path filePath = uploadPath.resolve(uniqueFileName);

        Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return "/uploads/" + uniqueFileName;
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/categories/{category}/subcategories")
    public List<String> getDistinctSubcategoriesByCategory(@PathVariable String category) {
        return productService.findDistinctSubcategoriesByCategory(category);
    }

    @GetMapping("/category/{category}/subcategory/{subcategory}")
    public ResponseEntity<List<Product>> getProductsByCategoryAndSubcategory(@PathVariable String category, @PathVariable String subcategory) {
        List<Product> products = productService.getProductsByCategoryAndSubcategory(category, subcategory);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        System.out.println("Fetching product with ID: " + productId);
        Product product = productService.getProductById(productId);
        if (product != null) {
            System.out.println("Product found: " + product.getName());
            return ResponseEntity.ok(product);
        } else {
            System.out.println("Product not found for ID: " + productId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}

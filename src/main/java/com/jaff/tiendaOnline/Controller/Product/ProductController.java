package com.jaff.tiendaOnline.Controller.Product;

import com.jaff.tiendaOnline.Entity.Product;
import com.jaff.tiendaOnline.Service.Product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<?> createProduct(@RequestParam("name") String name,
                                           @RequestParam("description") String description,
                                           @RequestParam("stock") int stock,
                                           @RequestParam("price") double price,
                                           @RequestParam("category") String category,
                                           @RequestParam("subcategory") String subcategory,
                                           @RequestPart(required = false) List<MultipartFile> images) {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setStock(stock);
        product.setPrice(price);
        product.setCategory(category);
        product.setSubcategory(subcategory);

        try {
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
        String uploadDir = System.getProperty("user.dir") + "/uploads";
        System.out.println("Upload directory: " + uploadDir);

        File uploadDirFile = new File(uploadDir);
        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdirs();
        }

        // Check write permissions
        if (!uploadDirFile.canWrite()) {
            throw new IOException("Cannot write to upload directory: " + uploadDir);
        }

        String originalFileName = image.getOriginalFilename();
        String uniqueFileName = System.currentTimeMillis() + "_" + originalFileName;
        String filePath = uploadDir + File.separator + uniqueFileName;

        Path destinationPath = Paths.get(filePath);
        Files.copy(image.getInputStream(), destinationPath, StandardCopyOption.REPLACE_EXISTING);

        // Log the file path for debugging
        System.out.println("Image saved to: " + filePath);

        return "/uploads/" + uniqueFileName;
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

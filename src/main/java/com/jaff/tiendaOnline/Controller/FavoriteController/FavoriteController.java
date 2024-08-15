package com.jaff.tiendaOnline.Controller.FavoriteController;

import com.jaff.tiendaOnline.Entity.Product;
import com.jaff.tiendaOnline.Service.favorite.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @PostMapping("/add")
    public ResponseEntity<Void> addFavoriteProduct(@RequestParam Long productId, @RequestParam String email) {
        favoriteService.addFavoriteProductByEmail(email, productId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/remove")
    public ResponseEntity<Void> removeFavoriteProduct(@RequestParam Long productId, @RequestParam String email) {
        favoriteService.removeFavoriteProductByEmail(email, productId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Set<Product>> getFavoriteProducts(@RequestParam String email) {
        Set<Product> favoriteProducts = favoriteService.getFavoriteProductsByEmail(email);
        return ResponseEntity.ok(favoriteProducts);
    }
}

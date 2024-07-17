package com.jaff.tiendaOnline.Controller.Cart;

import com.jaff.tiendaOnline.Entity.Cart;
import com.jaff.tiendaOnline.Entity.CartItem;
import com.jaff.tiendaOnline.Service.cart.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/{cartId}/items")
    public ResponseEntity<CartItem> addItemToCart(
            @RequestHeader(value = "User-Role", defaultValue = "guest") String userRole,
            @PathVariable(required = false) Long cartId,
            @RequestBody CartItem cartItem) {
        CartItem addedItem;
        if ("guest".equals(userRole)) {
            addedItem = cartService.addItemToCart(null, cartItem); // Guest cart
        } else {
            addedItem = cartService.addItemToCart(cartId, cartItem); // Registered user cart
        }
        return ResponseEntity.ok(addedItem);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<Cart> getCartById(
            @RequestHeader(value = "User-Role", defaultValue = "guest") String userRole,
            @PathVariable Long cartId) {
        Cart cart;
        if ("guest".equals(userRole)) {
            cart = cartService.getCartById(cartId); // Guest cart
        } else {
            cart = cartService.getCartById(cartId); // Registered user cart
        }
        if (cart == null) {
            throw new RuntimeException("Cart not found with id: " + cartId);
        }
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/new")
    public ResponseEntity<Cart> createNewCart(
            @RequestHeader(value = "User-Role", defaultValue = "guest") String userRole) {
        Cart newCart;
        if ("guest".equals(userRole)) {
            newCart = cartService.createNewCart(); // Guest cart
        } else {
            newCart = cartService.createNewCart(); // Registered user cart
        }
        return ResponseEntity.ok(newCart);
    }

    @PutMapping("/{cartId}/items/{cartItemId}")
    public ResponseEntity<CartItem> updateCartItemQuantity(
            @RequestHeader(value = "User-Role", defaultValue = "guest") String userRole,
            @PathVariable Long cartId,
            @PathVariable Long cartItemId,
            @RequestParam int quantity) {
        CartItem updatedItem;
        if ("guest".equals(userRole)) {
            updatedItem = cartService.updateCartItemQuantity(cartId, cartItemId, quantity); // Guest cart
        } else {
            updatedItem = cartService.updateCartItemQuantity(cartId, cartItemId, quantity); // Registered user cart
        }
        return ResponseEntity.ok(updatedItem);
    }

    @DeleteMapping("/{cartId}/items/{cartItemId}")
    public ResponseEntity<?> removeItemFromCart(
            @RequestHeader(value = "User-Role", defaultValue = "guest") String userRole,
            @PathVariable Long cartId,
            @PathVariable Long cartItemId) {
        if ("guest".equals(userRole)) {
            cartService.removeItemFromCart(cartId, cartItemId); // Guest cart
        } else {
            cartService.removeItemFromCart(cartId, cartItemId); // Registered user cart
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{cartId}/clear")
    public ResponseEntity<?> clearCart(
            @RequestHeader(value = "User-Role", defaultValue = "guest") String userRole,
            @PathVariable Long cartId) {
        if ("guest".equals(userRole)) {
            cartService.clearCart(cartId); // Guest cart
        } else {
            cartService.clearCart(cartId); // Registered user cart
        }
        return ResponseEntity.ok().build();
    }
}

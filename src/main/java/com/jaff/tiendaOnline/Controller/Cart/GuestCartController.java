package com.jaff.tiendaOnline.Controller.Cart;

import com.jaff.tiendaOnline.Entity.Cart;
import com.jaff.tiendaOnline.Entity.CartItem;
import com.jaff.tiendaOnline.Service.cart.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/guest-cart")
public class GuestCartController {

    private final CartService cartService;

    public GuestCartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/new")
    public ResponseEntity<Cart> createNewCart() {
        Cart newCart = cartService.createNewCart();
        return ResponseEntity.ok(newCart);
    }

    @PostMapping("/{cartId}/items")
    public ResponseEntity<CartItem> addItemToGuestCart(@PathVariable Long cartId, @RequestBody CartItem cartItem) {
        CartItem addedItem = cartService.addItemToCart(cartId, cartItem);
        return ResponseEntity.ok(addedItem);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<Cart> getGuestCartById(@PathVariable Long cartId) {
        Cart cart = cartService.getCartById(cartId);
        return ResponseEntity.ok(cart);
    }

    @PutMapping("/{cartId}/items/{cartItemId}")
    public ResponseEntity<CartItem> updateGuestCartItemQuantity(@PathVariable Long cartId, @PathVariable Long cartItemId, @RequestParam int quantity) {
        CartItem updatedItem = cartService.updateCartItemQuantity(cartId, cartItemId, quantity);
        return ResponseEntity.ok(updatedItem);
    }

    @DeleteMapping("/{cartId}/items/{cartItemId}")
    public ResponseEntity<?> removeGuestItemFromCart(@PathVariable Long cartId, @PathVariable Long cartItemId) {
        cartService.removeItemFromCart(cartId, cartItemId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{cartId}/clear")
    public ResponseEntity<?> clearGuestCart(@PathVariable Long cartId) {
        cartService.clearCart(cartId);
        return ResponseEntity.ok().build();
    }
}

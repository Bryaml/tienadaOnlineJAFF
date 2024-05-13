package com.jaff.tiendaOnline.Controller.Cart;

import com.jaff.tiendaOnline.Entity.Cart;
import com.jaff.tiendaOnline.Entity.CartItem;
import com.jaff.tiendaOnline.Service.cart.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/{cartId}/items")
    public ResponseEntity<CartItem> addItemToCart(@PathVariable Long cartId, @RequestBody CartItem cartItem) {
        CartItem addedItem = cartService.addItemToCart(cartId, cartItem);
        return ResponseEntity.ok(addedItem);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<Cart> getCartById(@PathVariable Long cartId) {
        Cart cart = cartService.getCartById(cartId);
        return ResponseEntity.ok(cart);
    }

    @PutMapping("/{cartId}/items/{cartItemId}")
    public ResponseEntity<CartItem> updateCartItemQuantity(
            @PathVariable Long cartId,
            @PathVariable Long cartItemId,
            @RequestParam int quantity) {
        CartItem updatedItem = cartService.updateCartItemQuantity(cartId, cartItemId, quantity);
        return ResponseEntity.ok(updatedItem);
    }

    @DeleteMapping("/{cartId}/items/{cartItemId}")
    public ResponseEntity<?> removeItemFromCart(@PathVariable Long cartId, @PathVariable Long cartItemId) {
        cartService.removeItemFromCart(cartId, cartItemId);
        return ResponseEntity.ok().build();
    }

    // Otros métodos para procesar el pago, vaciar el carrito, etc.
}

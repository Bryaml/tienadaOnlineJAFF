package com.jaff.tiendaOnline.Service.cart;

import com.jaff.tiendaOnline.Entity.Cart;
import com.jaff.tiendaOnline.Entity.CartItem;
import com.jaff.tiendaOnline.Entity.Product;
import com.jaff.tiendaOnline.Repository.CartItemRepository;
import com.jaff.tiendaOnline.Repository.CartRepository;
import com.jaff.tiendaOnline.Repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;



@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
    }

    public CartItem addItemToCart(Long cartId, CartItem cartItem) {
        Cart cart;
        if (cartId == null || !cartRepository.existsById(cartId)) {
            cart = new Cart();
            cart.setCartItems(new ArrayList<>());
            cart = cartRepository.save(cart);
        } else {
            cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found with id: " + cartId));
        }

        Product product = productRepository.findById(cartItem.getProduct().getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + cartItem.getProduct().getProductId()));

        if (product.getStock() < cartItem.getQuantity()) {
            throw new RuntimeException("Product " + product.getName() + " is not available in sufficient quantity.");
        }

        cartItem.setCart(cart);
        cartItem.setProduct(product);

        product.setStock(product.getStock() - cartItem.getQuantity());
        productRepository.save(product);

        return cartItemRepository.save(cartItem);
    }

    public Cart createNewCart() {
        Cart cart = new Cart();
        cart.setCartItems(new ArrayList<>());
        return cartRepository.save(cart);
    }

    public Cart getCartById(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setCartItems(new ArrayList<>());
                    return cartRepository.save(newCart);
                });
    }

    public CartItem updateCartItemQuantity(Long cartId, Long cartItemId, int quantity) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found with id: " + cartId));

        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("CartItem not found with id: " + cartItemId));

        cartItem.setQuantity(quantity);
        return cartItemRepository.save(cartItem);
    }

    public void removeItemFromCart(Long cartId, Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    public void clearCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found with id: " + cartId));
        cartItemRepository.deleteAll(cart.getCartItems());
    }
}

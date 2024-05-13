package com.jaff.tiendaOnline.Service.cart;

import com.jaff.tiendaOnline.Entity.Cart;
import com.jaff.tiendaOnline.Entity.CartItem;
import com.jaff.tiendaOnline.Entity.Product;
import com.jaff.tiendaOnline.Repository.CartItemRepository;
import com.jaff.tiendaOnline.Repository.CartRepository;
import com.jaff.tiendaOnline.Repository.ProductRepository;
import org.springframework.stereotype.Service;
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
        // Busca el carrito por su ID
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        Cart cart = optionalCart.orElseThrow(() -> new RuntimeException("Cart not found with id: " + cartId));

        // Obtén el producto del cartItem
        Product product = productRepository.findById(cartItem.getProduct().getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + cartItem.getProduct().getProductId()));

        // Verifica la disponibilidad del producto
        if (product.getStock() < cartItem.getQuantity()) {
            throw new RuntimeException("Product " + product.getName() + " is not available in sufficient quantity.");
        }

        // Si el producto está disponible, agrégalo al carrito y guarda el CartItem
        cartItem.setCart(cart);
        cartItem.setProduct(product);

        // Reduce la cantidad del producto en el inventario
        product.setStock(product.getStock() - cartItem.getQuantity());
        productRepository.save(product);

        return cartItemRepository.save(cartItem);
    }


    public Cart getCartById(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found with id: " + cartId));
    }

    public CartItem updateCartItemQuantity(Long cartId, Long cartItemId, int quantity) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        Cart cart = optionalCart.orElseThrow(() -> new RuntimeException("Cart not found with id: " + cartId));

        Optional<CartItem> optionalCartItem = cartItemRepository.findById(cartItemId);
        CartItem cartItem = optionalCartItem.orElseThrow(() -> new RuntimeException("CartItem not found with id: " + cartItemId));

        // Aquí podrías implementar lógica adicional, como verificar la disponibilidad del producto, etc.

        cartItem.setQuantity(quantity);
        return cartItemRepository.save(cartItem);
    }

    public void removeItemFromCart(Long cartId, Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    // Otros métodos para procesar el pago, vaciar el carrito, etc.
}


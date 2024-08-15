package com.jaff.tiendaOnline.Service.favorite;

import com.jaff.tiendaOnline.Entity.Customer;
import com.jaff.tiendaOnline.Entity.Product;
import com.jaff.tiendaOnline.Repository.CustomerRepository;
import com.jaff.tiendaOnline.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class FavoriteService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    public void addFavoriteProductByEmail(String email, Long productId) {
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        customer.getFavoriteProducts().add(product);
        customerRepository.save(customer);
    }

    public void removeFavoriteProductByEmail(String email, Long productId) {
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        customer.getFavoriteProducts().remove(product);
        customerRepository.save(customer);
    }

    public Set<Product> getFavoriteProductsByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return customer.getFavoriteProducts();
    }
}

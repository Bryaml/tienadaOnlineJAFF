package com.jaff.tiendaOnline.Service.orders;

import com.jaff.tiendaOnline.Entity.Customer;
import com.jaff.tiendaOnline.Entity.OrderDetails;
import com.jaff.tiendaOnline.Entity.Orders;
import com.jaff.tiendaOnline.Entity.Product;
import com.jaff.tiendaOnline.Repository.CartRepository;
import com.jaff.tiendaOnline.Repository.OrderDetailsRepository;
import com.jaff.tiendaOnline.Repository.OrderRepository;
import com.jaff.tiendaOnline.Repository.ProductRepository;
import com.jaff.tiendaOnline.Service.Product.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Orders createOrder(Orders order) {
        return orderRepository.save(order);
    }

    public Orders getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
}

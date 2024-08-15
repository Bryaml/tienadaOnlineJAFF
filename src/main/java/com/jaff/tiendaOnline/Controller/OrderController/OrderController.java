package com.jaff.tiendaOnline.Controller.OrderController;

import com.jaff.tiendaOnline.Entity.Customer;
import com.jaff.tiendaOnline.Entity.Orders;
import com.jaff.tiendaOnline.Repository.CustomerRepository;
import com.jaff.tiendaOnline.Service.Product.ResourceNotFoundException;
import com.jaff.tiendaOnline.Service.orders.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<Orders> createOrder(@RequestBody Orders order) {
        Orders createdOrder = orderService.createOrder(order);
        return ResponseEntity.ok(createdOrder);
    }
}

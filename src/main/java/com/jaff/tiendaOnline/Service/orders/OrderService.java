package com.jaff.tiendaOnline.Service.orders;

import com.jaff.tiendaOnline.Entity.OrderDetails;
import com.jaff.tiendaOnline.Entity.Orders;
import com.jaff.tiendaOnline.Repository.OrderDetailsRepository;
import com.jaff.tiendaOnline.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    public Orders addOrderDetails(Long orderId, OrderDetails orderDetails) {
        // Busca la orden existente por su ID
        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));

        // Establece la relación bidireccional entre la orden y los detalles de la orden
        orderDetails.setOrder(order);
        // Guarda los detalles de la orden en la base de datos
        orderDetailsRepository.save(orderDetails);

        // Agrega los detalles de la orden a la lista de detalles de la orden de la orden
        order.getOrderDetails().add(orderDetails);
        // Actualiza la orden en la base de datos para reflejar los nuevos detalles de la orden agregados
        return orderRepository.save(order);
    }

    // Otros métodos del servicio...
}

package com.jaff.tiendaOnline.Repository;

import com.jaff.tiendaOnline.Entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetails, Long> {
    // Puedes agregar métodos personalizados de consulta aquí si es necesario
}

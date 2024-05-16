package com.jaff.tiendaOnline.Repository;

import com.jaff.tiendaOnline.Entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {
}
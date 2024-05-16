package com.jaff.tiendaOnline.Repository;

import com.jaff.tiendaOnline.Entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
}
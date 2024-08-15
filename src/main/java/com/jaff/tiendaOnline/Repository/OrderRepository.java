package com.jaff.tiendaOnline.Repository;

import com.jaff.tiendaOnline.Entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByCustomerCustomerId(Long customerId);
}

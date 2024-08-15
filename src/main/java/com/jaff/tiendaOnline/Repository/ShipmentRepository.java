package com.jaff.tiendaOnline.Repository;

import com.jaff.tiendaOnline.Entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
    Optional<Shipment> findByOrdersOrderId(Long orderId);
}

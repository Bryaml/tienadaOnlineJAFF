package com.jaff.tiendaOnline.Repository;

import com.jaff.tiendaOnline.Entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {}
package com.jaff.tiendaOnline.Repository;

import com.jaff.tiendaOnline.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
        Optional<Customer> findByEmail(String email);
        @Query("SELECT c FROM Customer c JOIN FETCH c.favoriteProducts WHERE c.customerId = :customerId")
        Optional<Customer> findByIdWithFavorites(@Param("customerId") Long customerId);

}
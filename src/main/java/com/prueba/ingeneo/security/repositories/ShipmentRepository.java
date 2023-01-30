package com.prueba.ingeneo.security.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.ingeneo.security.models.Customer;
import com.prueba.ingeneo.security.models.Product;
import com.prueba.ingeneo.security.models.Shipment;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment,Long> {

    List<Shipment> findByCustomerId(Customer shipmentId);
}

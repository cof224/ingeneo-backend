package com.prueba.ingeneo.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.ingeneo.security.models.Warehouse;

import java.util.List;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
	
	List<Warehouse> findByNameContaining(String name);

	Warehouse findByType(Integer type);
}

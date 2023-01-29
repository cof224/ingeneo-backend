package com.prueba.ingeneo.security.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.ingeneo.security.models.Service;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Integer> {

    List<Service> findByNameContaining(String name);

}

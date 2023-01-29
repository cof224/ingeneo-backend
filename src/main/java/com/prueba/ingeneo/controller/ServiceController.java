package com.prueba.ingeneo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.prueba.ingeneo.exception.ResourceNotFoundException;
import com.prueba.ingeneo.security.models.Service;
import com.prueba.ingeneo.security.repositories.ServiceRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test/service")
public class ServiceController {
    @Autowired
    ServiceRepository serviceRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Service>> getAll(@RequestParam(required = false) String name) {
        List<Service> servicio = new ArrayList<Service>();
        if (name == null) {
            serviceRepository.findAll().forEach(servicio::add);
        }else {
            serviceRepository.findByNameContaining(name).forEach(servicio::add);
        }
        
        if (servicio.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        return new ResponseEntity<>(servicio, HttpStatus.OK);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Service> create(@Valid @RequestBody Service ser){
        Service servicio = serviceRepository.save(new Service(ser.getName(), ser.getDescription()));
        return new ResponseEntity<>(servicio, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Service> findById(@PathVariable("id") Integer id){
        Service servicio = serviceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No se encuentra el id = " + id));
        return new ResponseEntity<>(servicio, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Service> update(@PathVariable("id") Integer id, @RequestBody Service request) {
        Service servicio  = serviceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No se encontro el servicio con id = " + id));
        servicio.setName(request.getName());
        servicio.setDescription(request.getDescription());
        return new ResponseEntity<>(serviceRepository.save(servicio), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Integer id) {
        serviceRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}

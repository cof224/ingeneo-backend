package com.prueba.ingeneo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.prueba.ingeneo.exception.ResourceNotFoundException;
import com.prueba.ingeneo.security.models.Warehouse;
import com.prueba.ingeneo.security.repositories.WarehouseRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test/warehouse")
public class WareHouseController {

    @Autowired
    WarehouseRepository warehouseRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Warehouse>> getAllWareHouse(@RequestParam(required = false) String name) {
        
    	List<Warehouse> warehouse = new ArrayList<Warehouse>();
       
        if (name == null) {
            warehouseRepository.findAll().forEach(warehouse::add);
        }else {
            warehouseRepository.findByNameContaining(name).forEach(warehouse::add);
        }
        
        if (warehouse.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        return new ResponseEntity<>(warehouse, HttpStatus.OK);
    }

    
    @PostMapping("/add")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Warehouse> createWareHouse(@Valid @RequestBody Warehouse request){
        Warehouse warehouse = warehouseRepository.save(new Warehouse(request.getName(), request.getType(),true));
        return new ResponseEntity<>(warehouse, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Warehouse> findById(@PathVariable("id") Long id){
    	
        Warehouse warehouse = warehouseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No se encuentra el id = " + id));
        return new ResponseEntity<>(warehouse, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Warehouse> updateWareHouse(@PathVariable("id") Long id, @RequestBody Warehouse request) {
        
    	Warehouse  warehouse  = warehouseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No se encontro el registro con id = " + id));
    	warehouse.setName(request.getName());
    	warehouse.setEnabled(request.getEnabled());
    	warehouse.setType(request.getType());

        return new ResponseEntity<>(warehouseRepository.save(warehouse), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deleteWareHouse(@PathVariable("id") Long id) {
        warehouseRepository.deleteById(id);
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

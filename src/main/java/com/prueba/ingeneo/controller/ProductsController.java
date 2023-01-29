package com.prueba.ingeneo.controller;


import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.ingeneo.exception.ResourceNotFoundException;
import com.prueba.ingeneo.security.models.Product;
import com.prueba.ingeneo.security.repositories.ProductRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test/product")
public class ProductsController {


    @Autowired
    ProductRepository productRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAll(@RequestParam(required = false) String name) {
        List<Product> productos = new ArrayList<Product>();
        if (name == null) {
            productRepository.findAll().forEach(productos::add);
        }else {
            productRepository.findByNameContainingAndEnabledIsTrue(name).forEach(productos::add);
        }
        
        if (productos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> createService(@Valid @RequestBody Product p){
        Product prod = productRepository.save(new Product(p.getName(), p.getDescription(), p.getPrice(), true));
        return new ResponseEntity<>(prod, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Product> findById(@PathVariable("id") Long id){
        Product producto = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No se encuentra el id = " + id));
        return new ResponseEntity<>(producto, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Product> update(@PathVariable("id") Long id, @RequestBody Product request) {
        Product productos  = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No se encontro el registro con id = " + id));

        productos.setName(request.getName());
        productos.setDescription(request.getDescription());
        productos.setPrice(request.getPrice());

        return new ResponseEntity<>(productRepository.save(productos), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        productRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

package com.prueba.ingeneo.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
import com.prueba.ingeneo.security.models.Customer;
import com.prueba.ingeneo.security.repositories.CustomerRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test/customer")
public class CustomerController {

	@Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAll(@RequestParam(required = false) String name) {
        List<Customer> clientes = new ArrayList<Customer>();
        
        if (name == null) {
            customerRepository.findAll().forEach(clientes::add);
        }else {
            customerRepository.findByNameContaining(name).forEach(clientes::add);
        }
        
        if (clientes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer cliente){
        LocalDateTime lt = LocalDateTime.now();
        Customer newcliente = customerRepository.save(new Customer(cliente.getName(),cliente.getPhone(),cliente.getAddress(),cliente.getEmail(),cliente.getActive(),lt));
        return new ResponseEntity<>(newcliente, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Customer> findById(@PathVariable("id") Long id){
        Customer clientes = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encuentra el id = " + id));
        return new ResponseEntity<>(clientes, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Customer> update(@PathVariable("id") Long id, @RequestBody Customer request) {
        Customer cliente  = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No se encontro el cliente con id = " + id));

        cliente.setName(request.getName());
        cliente.setEmail(request.getEmail());
        cliente.setAddress(request.getAddress());
        cliente.setPhone(request.getPhone());
        cliente.setActive(request.getActive());

        return new ResponseEntity<>(customerRepository.save(cliente), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        Customer customers  = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro el cliente con id = " + id));
        customers.setActive(false);
        customerRepository.save(customers);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    
}
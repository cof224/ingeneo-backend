package com.prueba.ingeneo.controller;

import com.prueba.ingeneo.exception.ResourceNotFoundException;
import com.prueba.ingeneo.payload.request.ShipmentRequest;
import com.prueba.ingeneo.security.models.Customer;
import com.prueba.ingeneo.security.models.Product;
import com.prueba.ingeneo.security.models.Service;
import com.prueba.ingeneo.security.models.Shipment;
import com.prueba.ingeneo.security.models.Warehouse;
import com.prueba.ingeneo.security.repositories.CustomerRepository;
import com.prueba.ingeneo.security.repositories.ProductRepository;
import com.prueba.ingeneo.security.repositories.ServiceRepository;
import com.prueba.ingeneo.security.repositories.ShipmentRepository;
import com.prueba.ingeneo.security.repositories.WarehouseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test/shipment")
public class ShipmentController {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    ShipmentRepository shipmentRepository;
    @Autowired
    ServiceRepository serviceRepository;
    @Autowired
    CustomerRepository customerRepository;

    /*
     * Agregar un shipment y aplicar descuentos si aplica
     */
    @PostMapping("/add")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Shipment> createShipment(@Valid @RequestBody ShipmentRequest request){
    	
    	Shipment shipment= new Shipment();
    	shipment.setCustomer(new Customer());
    	shipment.setProduct(new Product());
    	shipment.setService(new Service());
    	shipment.setWarehouse(new Warehouse());

        Pattern pattern1 ;
        Matcher matcher1 ;
        
        if(isValid(request)){
        	shipment.setCustomer(customerRepository.findById(request.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("No se encuentra cliente con el identificador = " + request.getCustomerId())));
        	shipment.setService(serviceRepository.findById(request.getServiceId()).orElseThrow(() -> new ResourceNotFoundException("No hay servicio configurado = " + request.getServiceId())));
        	shipment.setWarehouse(warehouseRepository.findById(request.getWarehouseId()).orElseThrow(() -> new ResourceNotFoundException("No hay almacen configurado = " + request.getWarehouseId())));

        	shipment.setProduct(productRepository.findById(request.getProductId()).orElseThrow(() -> new ResourceNotFoundException("No se encuentra este producto= " + request.getProductId())));

        	//Dependiento del tipo de servicio asi se genera el patron a utilizar para genera la placa del vehiculo o el numero de flota 
            if(shipment.getService().getId()==1){
            	//placa del vehiculo
                pattern1 = Pattern.compile("^[A-Z]{3}[0-9]{3}$");
            }else{
            	//numero de flota
                pattern1 =Pattern.compile("^[A-Z]{3}[0-9]{4}[A-Z]{1}$");
            }
            
            matcher1 = pattern1.matcher(request.getNumber());
            
            if(!matcher1.find()){
                throw new ResourceNotFoundException("Numero de transporte no coincide con el formato");
            }
            
            shipment.setTransportNumber(request.getNumber());
            shipment.setTrackingNumber(generarTrackingNumber());
            shipment.setShipmentDate(request.getShipmentDate());
            shipment.setEnabled(true);
            shipment.setQuantity(request.getQuantity());
            
            if(shipment.getQuantity()>=10 ){
                if(shipment.getService().getId()==1){
                	shipment.setDiscount(new BigDecimal("0.05"));
                }else{
                	shipment.setDiscount(new BigDecimal("0.05"));
                }
                
                shipment.setTotal(shipment.getProduct().getPrice().multiply(new BigDecimal(shipment.getQuantity())));
                shipment.setTotal(shipment.getTotal().multiply(shipment.getDiscount()));

            }else{
            	shipment.setTotal(shipment.getProduct().getPrice().multiply(new BigDecimal(shipment.getQuantity())));
            	shipment.setDiscount(BigDecimal.ZERO);
            }
            
            shipment.setCreatedDate(LocalDateTime.now());
            shipmentRepository.save(shipment);
            return new ResponseEntity<>(shipment, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    
    /**
     * buscar todos los shipments por cliente
     * @param id
     * @return
     */
    @GetMapping("/allByClientId/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<Shipment>> getAll(@PathVariable("id") Long id) {
        List<Shipment> shipments = new ArrayList<Shipment>();
        Customer cliente = new Customer();
       
        if (id <= 0 ) {
            throw  new ResourceNotFoundException("Debe ingresar un cliente existente  " + id);
        }else {
        	cliente = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No se encuentra cliente con el identificador = " + id));
        }
        
        shipments = shipmentRepository.findByCustomerId(cliente);
       
        if (shipments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        return new ResponseEntity<>(shipments, HttpStatus.OK);
    }
    
    /**
     * verificar si la peticion de envio es valido.
     * @param d
     * @return
     */
    public Boolean isValid (ShipmentRequest peticionDeEnvio){

        // producto
        if (peticionDeEnvio.getProductId() <= 0 )
            throw  new ResourceNotFoundException("Debe ingresar un producto valido  " + peticionDeEnvio.getProductId());
        //cantidad
        if (peticionDeEnvio.getQuantity() <= 0 )
            throw  new ResourceNotFoundException("La cantidad de producto es requerido " + peticionDeEnvio.getQuantity());
      
        //servicio
        if (peticionDeEnvio.getServiceId() <= 0 )
            throw  new ResourceNotFoundException("Debe ingresar un cliente existente  " + peticionDeEnvio.getServiceId());

        //cliente
        if (peticionDeEnvio.getCustomerId() <= 0 )
            throw  new ResourceNotFoundException("Debe ingresar un cliente existente  " + peticionDeEnvio.getCustomerId());

        //fecha de entrega
        if(peticionDeEnvio.getShipmentDate() == null)
            throw  new ResourceNotFoundException("Fecha de entrega es requerida" + peticionDeEnvio.getShipmentDate());
        
        // numero de transporte
        if(peticionDeEnvio.getNumber() == null)
            throw  new ResourceNotFoundException("El numero de transporte es requerido" + peticionDeEnvio.getQuantity());

        return true;
    }

    /**
     * Metodo para generar el tracking number
     * @return
     */
    public String  generarTrackingNumber() {

        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
       
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        
        String generatedString = buffer.toString();

        return generatedString;
    }


}

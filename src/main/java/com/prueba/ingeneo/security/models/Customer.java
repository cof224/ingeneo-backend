package com.prueba.ingeneo.security.models;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "customers", schema = "transporte")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 50)
    @NotNull
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Size(max = 100)
    @NotNull
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Size(max = 8)
    @NotNull
    @Column(name = "phone", nullable = false, length = 8)
    private String phone;

    @Size(max = 100)
    @NotNull
    @Column(name = "address", nullable = false, length = 100)
    private String address;

    @Column(name = "active")
    private Boolean active;

    @NotNull
    @Column(name = "created_date", nullable = false)
    @CreatedDate
    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "customer")
    private Set<Shipment> shipments = new LinkedHashSet<>();

    
    public Customer() {
		super();
	}


	public Customer(String name,String phone, String address, String email, Boolean active,LocalDateTime createdDate) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.active = active;
		this.createdDate = createdDate;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public Boolean getActive() {
		return active;
	}


	public void setActive(Boolean active) {
		this.active = active;
	}


	public LocalDateTime getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}


	public Set<Shipment> getShipments() {
		return shipments;
	}


	public void setShipments(Set<Shipment> shipments) {
		this.shipments = shipments;
	}


    
}
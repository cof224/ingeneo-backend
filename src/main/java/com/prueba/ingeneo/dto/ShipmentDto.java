package com.prueba.ingeneo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ShipmentDto {

	    private Long id;
	    private Long customer;
	    private String nameCustomer;
	    private Long warehouse;
	    private Long product;
	    private String nameproducto;
	    private Long service;
 	    private Integer quantity;
	    private LocalDateTime shipmentDate;
	    private BigDecimal discount;
	    private BigDecimal total;
	    private String trackingNumber;
	    private String transportNumber;
	    private Boolean enabled;
	    private LocalDateTime createdDate;
		public ShipmentDto() {
		
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public Long getCustomer() {
			return customer;
		}
		public void setCustomer(Long customer) {
			this.customer = customer;
		}
		public Long getWarehouse() {
			return warehouse;
		}
		public void setWarehouse(Long warehouse) {
			this.warehouse = warehouse;
		}
		public Long getProduct() {
			return product;
		}
		public void setProduct(Long product) {
			this.product = product;
		}
		public Long getService() {
			return service;
		}
		public void setService(Long service) {
			this.service = service;
		}
		public Integer getQuantity() {
			return quantity;
		}
		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}
		public LocalDateTime getShipmentDate() {
			return shipmentDate;
		}
		public void setShipmentDate(LocalDateTime shipmentDate) {
			this.shipmentDate = shipmentDate;
		}
		public BigDecimal getDiscount() {
			return discount;
		}
		public void setDiscount(BigDecimal discount) {
			this.discount = discount;
		}
		public BigDecimal getTotal() {
			return total;
		}
		public void setTotal(BigDecimal total) {
			this.total = total;
		}
		public String getTrackingNumber() {
			return trackingNumber;
		}
		public void setTrackingNumber(String trackingNumber) {
			this.trackingNumber = trackingNumber;
		}
		public String getTransportNumber() {
			return transportNumber;
		}
		public void setTransportNumber(String transportNumber) {
			this.transportNumber = transportNumber;
		}
		public Boolean getEnabled() {
			return enabled;
		}
		public void setEnabled(Boolean enabled) {
			this.enabled = enabled;
		}
		public LocalDateTime getCreatedDate() {
			return createdDate;
		}
		public void setCreatedDate(LocalDateTime createdDate) {
			this.createdDate = createdDate;
		}
		public String getNameproducto() {
			return nameproducto;
		}
		public void setNameproducto(String nameproducto) {
			this.nameproducto = nameproducto;
		}
		public String getNameCustomer() {
			return nameCustomer;
		}
		public void setNameCustomer(String nameCustomer) {
			this.nameCustomer = nameCustomer;
		}
	    
		
	    
}

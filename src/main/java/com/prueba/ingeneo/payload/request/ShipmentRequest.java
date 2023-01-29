package com.prueba.ingeneo.payload.request;

import java.io.Serializable;
import java.time.LocalDateTime;

@SuppressWarnings("serial")
public class ShipmentRequest implements Serializable {

    private Integer serviceId;
    private Long productId;
    private LocalDateTime shipmentDate;
    private Integer quantity;
    private Long customerId;
    private String number;
    private Long warehouseId;

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public LocalDateTime getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(LocalDateTime shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }
}

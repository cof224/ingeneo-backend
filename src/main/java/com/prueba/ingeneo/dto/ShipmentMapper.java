package com.prueba.ingeneo.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.prueba.ingeneo.security.models.Shipment;

@Mapper
public interface ShipmentMapper {

	ShipmentMapper INSTANCE = Mappers.getMapper( ShipmentMapper.class ); 

    ShipmentDto shipmentToShipmentDto(Shipment car);
}

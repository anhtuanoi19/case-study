package com.example.casestudy3.tranferDatas;

import com.example.casestudy3.dto.request.DeliveryDto;
import com.example.casestudy3.entity.Delivery;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DeliveryMapper {
    DeliveryMapper INSTANCE = Mappers.getMapper(DeliveryMapper.class);

    DeliveryDto toDto(Delivery delivery);

    Delivery toEntity(DeliveryDto deliveryDto);

    List<DeliveryDto> toDtoList(List<Delivery> deliveries);

    List<Delivery> toEntityList(List<DeliveryDto> deliveryDtos);
}

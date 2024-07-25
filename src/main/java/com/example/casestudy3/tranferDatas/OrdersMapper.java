package com.example.casestudy3.mapper;

import com.example.casestudy3.dto.request.OrdersDto;
import com.example.casestudy3.entity.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrdersMapper {
    OrdersMapper INSTANCE = Mappers.getMapper(OrdersMapper.class);

    OrdersDto toDto(Orders orders);

    Orders toEntity(OrdersDto ordersDto);

    List<OrdersDto> toDtoList(List<Orders> orders);

    List<Orders> toEntityList(List<OrdersDto> ordersDtos);
}

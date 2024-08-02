package com.example.casestudy3.tranferDatas;

import com.example.casestudy3.dto.request.CustomerDto;
import com.example.casestudy3.dto.request.DeliveryDto;
import com.example.casestudy3.dto.request.OrdersDto;
import com.example.casestudy3.entity.Customer;
import com.example.casestudy3.entity.Delivery;
import com.example.casestudy3.entity.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrdersMapper {
    OrdersMapper INSTANCE = Mappers.getMapper(OrdersMapper.class);
    Orders toEntity(OrdersDto ordersDto);

    OrdersDto toDto(Orders orders);

    Customer toCustomerEntity(CustomerDto customerDto);

    CustomerDto toDto(Customer customer);

    Delivery toDeliveryEntity(DeliveryDto deliveryDto);

    DeliveryDto toDto(Delivery delivery);

    List<Orders> toListEntity(List<OrdersDto> list);
    List<OrdersDto> toListDto(List<Orders> list);
}

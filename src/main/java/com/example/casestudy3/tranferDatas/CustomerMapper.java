package com.example.casestudy3.tranferDatas;

import com.example.casestudy3.dto.request.CustomerDto;
import com.example.casestudy3.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDto toDto(Customer customer);

    Customer toEntity(CustomerDto customerDto);

    List<CustomerDto> toDtoList(List<Customer> customers);

    List<Customer> toEntityList(List<CustomerDto> customerDtos);
}

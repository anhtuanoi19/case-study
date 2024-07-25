//package com.example.casestudy3.tranferDatas;
//
//import com.example.casestudy3.dto.request.*;
//import com.example.casestudy3.entity.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class TranferDatas {
//    public static CategoryDto convertToDto(Categories entity) {
//        CategoryDto dto = new CategoryDto();
//        if (entity.getId() != null) dto.setId(entity.getId());
//        if (entity.getCode() != null) dto.setCode(entity.getCode());
//        if (entity.getName() != null) dto.setName(entity.getName());
//        if (entity.getStatus() != null) dto.setStatus(entity.getStatus());
//        return dto;
//    }
//
//    public static Categories convertToEntity(CategoryDto dto) {
//        Categories entity = new Categories();
//        if (dto.getId() != null) entity.setId(dto.getId());
//        if (dto.getCode() != null) entity.setCode(dto.getCode());
//        if (dto.getName() != null) entity.setName(dto.getName());
//        if (dto.getStatus() != null) entity.setStatus(dto.getStatus());
//        return entity;
//    }
//
//    public static List<CategoryDto> convertListCategory(List<Categories> entityList) {
//        List<CategoryDto> dtoList = new ArrayList<>();
//        for (Categories entity : entityList) {
//            dtoList.add(convertToDto(entity));
//        }
//        return dtoList;
//    }
//
//    public static CustomerDto convertToDto(Customer entity) {
//        CustomerDto dto = new CustomerDto();
//        if (entity.getId() != null) dto.setId(entity.getId());
//        if (entity.getAdress() != null) dto.setAdress(entity.getAdress());
//        if (entity.getName() != null) dto.setName(entity.getName());
//        if (entity.getStatus() != null) dto.setStatus(entity.getStatus());
//        if (entity.getEmail() != null) dto.setEmail(entity.getEmail());
//        return dto;
//    }
//
//    public static Customer convertToEntity(CustomerDto dto) {
//        Customer entity = new Customer();
//        if (dto.getId() != null) entity.setId(dto.getId());
//        if (dto.getAdress() != null) entity.setAdress(dto.getAdress());
//        if (dto.getName() != null) entity.setName(dto.getName());
//        if (dto.getStatus() != null) entity.setStatus(dto.getStatus());
//        if (dto.getEmail() != null) entity.setEmail(dto.getEmail());
//        return entity;
//    }
//
//    public static List<CustomerDto> convertListCustomer(List<Customer> entityList) {
//        List<CustomerDto> dtoList = new ArrayList<>();
//        for (Customer entity : entityList) {
//            dtoList.add(convertToDto(entity));
//        }
//        return dtoList;
//    }
//
//    public static DeliveryDto convertToDto(Delivery entity) {
//        DeliveryDto dto = new DeliveryDto();
//        if (entity.getId() != null) dto.setId(entity.getId());
//        if (entity.getType() != null) dto.setType(entity.getType());
//        if (entity.getStatus() != null) dto.setStatus(entity.getStatus());
//        if (entity.getStatus() != null) dto.setStatus(entity.getStatus());
//        return dto;
//    }
//
//    public static Delivery convertToEntity(DeliveryDto dto) {
//        Delivery entity = new Delivery();
//        if (dto.getId() != null) entity.setId(dto.getId());
//        if (dto.getType() != null) entity.setType(dto.getType());
//        if (dto.getStatus() != null) entity.setStatus(dto.getStatus());
//        return entity;
//    }
//
//    public static List<DeliveryDto> convertListDelivery(List<Delivery> entityList) {
//        List<DeliveryDto> dtoList = new ArrayList<>();
//        for (Delivery entity : entityList) {
//            dtoList.add(convertToDto(entity));
//        }
//        return dtoList;
//    }
//
//    public static OrdersDto convertToDto(Orders entity) {
//        OrdersDto dto = new OrdersDto();
//        if (entity.getId() != null) dto.setId(entity.getId());
//        if (entity.getCustomer() != null) dto.setCustomer(entity.getCustomer());
//        if (entity.getDelivery() != null) dto.setDelivery(entity.getDelivery());
//        if (entity.getOrderDate() != null) dto.setOrderDate(entity.getOrderDate());
//        return dto;
//    }
//
//    public static Orders convertToEntity(OrdersDto dto) {
//        Orders entity = new Orders();
//        if (dto.getId() != null) entity.setId(dto.getId());
//        if (dto.getCustomer() != null) entity.setCustomer(dto.getCustomer());
//        if (dto.getDelivery() != null) entity.setDelivery(dto.getDelivery());
//        if (dto.getOrderDate() != null) entity.setOrderDate(dto.getOrderDate());
//        return entity;
//    }
//
//    public static List<OrdersDto> convertListOrders(List<Orders> entityList) {
//        List<OrdersDto> dtoList = new ArrayList<>();
//        for (Orders entity : entityList) {
//            dtoList.add(convertToDto(entity));
//        }
//        return dtoList;
//    }
//
//    public static ProductDto convertToDto(Product entity) {
//        ProductDto dto = new ProductDto();
//        if (entity.getId() != null) dto.setId(entity.getId());
//        if (entity.getName() != null) dto.setName(entity.getName());
//        if (entity.getCategories() != null) dto.setCategories(entity.getCategories());
//        if (entity.getOrders() != null) dto.setOrders(entity.getOrders());
//        if (entity.getStatus() != null) dto.setStatus(entity.getStatus());
//        return dto;
//    }
//
//    public static Product convertToEntity(ProductDto dto) {
//        Product entity = new Product();
//        if (dto.getId() != null) entity.setId(dto.getId());
//        if (dto.getName() != null) entity.setName(dto.getName());
//        if (dto.getCategories() != null) entity.setCategories(dto.getCategories());
//        if (dto.getOrders() != null) entity.setOrders(dto.getOrders());
//        if (dto.getStatus() != null) entity.setStatus(dto.getStatus());
//        return entity;
//    }
//
//    public static List<ProductDto> convertListProduct(List<Product> entityList) {
//        List<ProductDto> dtoList = new ArrayList<>();
//        for (Product entity : entityList) {
//            dtoList.add(convertToDto(entity));
//        }
//        return dtoList;
//    }
//
//}

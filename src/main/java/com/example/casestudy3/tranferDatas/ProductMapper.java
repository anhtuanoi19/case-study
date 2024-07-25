package com.example.casestudy3.tranferDatas;

import com.example.casestudy3.dto.request.ProductDto;
import com.example.casestudy3.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(uses = {CategoryMapper.class})
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "categories", source = "categories")
    @Mapping(target = "orders", source = "orders")
    ProductDto toDto(Product product);

    @Mapping(target = "categories", source = "categories")
    @Mapping(target = "orders", source = "orders")
    Product toEntity(ProductDto productDto);

    // Nếu cần ánh xạ tập hợp các đối tượng, có thể tạo thêm các phương thức khác cho Set<Product> và Set<ProductDto>
    Set<ProductDto> toDtoSet(Set<Product> products);

    Set<Product> toEntitySet(Set<ProductDto> productDtos);
}

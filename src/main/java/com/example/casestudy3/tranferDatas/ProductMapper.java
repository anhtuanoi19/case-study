package com.example.casestudy3.tranferDatas;

import com.example.casestudy3.dto.request.ProductDto;
import com.example.casestudy3.entity.Product;
import jakarta.transaction.Transactional;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(uses = {CategoryMapper.class, OrdersMapper.class})
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "categories", source = "categories")
    @Mapping(target = "orders", source = "orders")
    ProductDto toDto(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categories", source = "categories")
    @Mapping(target = "orders", source = "orders")
    Product toEntity(ProductDto productDto);

    // Nếu cần ánh xạ tập hợp các đối tượng, có thể tạo thêm các phương thức khác cho Set<Product> và Set<ProductDto>
    Set<ProductDto> toDtoSet(Set<Product> products);

    Set<Product> toEntitySet(Set<ProductDto> productDtos);

    @Transactional(Transactional.TxType.REQUIRED)
    void updateEntityFromDto(ProductDto productDto, @MappingTarget Product product);
}

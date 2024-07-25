package com.example.casestudy3.service.impl;

import com.example.casestudy3.dto.request.OrdersDto;
import com.example.casestudy3.dto.request.ProductDto;
import com.example.casestudy3.dto.response.ApiResponse;
import com.example.casestudy3.entity.Orders;
import com.example.casestudy3.entity.Product;
import com.example.casestudy3.exception.AppException;
import com.example.casestudy3.exception.ErrorCode;
import com.example.casestudy3.mapper.ProductMapper;
import com.example.casestudy3.repository.ProductRepository;
import com.example.casestudy3.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;
    private final ProductMapper productMapper = ProductMapper.INSTANCE;

    @Override
    public ApiResponse<ProductDto> create(ProductDto productDto) {
        Product product = productMapper.toEntity(productDto);
        product = productRepository.save(product);
        ProductDto result = productMapper.toDto(product);

        ApiResponse<ProductDto> apiResponse = new ApiResponse<>();
        apiResponse.setResult(result);
        apiResponse.setMessage(result != null ? "Tạo sản phẩm thành công" : "Lỗi trong quá trình tạo sản phẩm");

        return apiResponse;
    }

    @Override
    public ApiResponse<ProductDto> update(ProductDto productDto, UUID id) {
        ApiResponse<ProductDto> apiResponse = new ApiResponse<>();

        if (!productRepository.existsById(id)) {
            apiResponse.setMessage("Cập nhật thất bại");
        }

        Product product = productMapper.toEntity(productDto);
        product.setId(id);
        product = productRepository.save(product);
        ProductDto result = productMapper.toDto(product);

        apiResponse.setResult(result);
        apiResponse.setMessage(result != null ? "Cập nhật sản phẩm thành công" : "Lỗi trong quá trình cập nhật sản phẩm");

        return apiResponse;
    }

    @Override
    public ApiResponse<List<ProductDto>> getAll() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = products.stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());

        ApiResponse<List<ProductDto>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(productDtos);
        apiResponse.setMessage(!productDtos.isEmpty() ? "Lấy danh sách sản phẩm thành công" : "Không có sản phẩm nào");

        return apiResponse;
    }

    @Override
    public ApiResponse<ProductDto> findById(UUID id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        ProductDto result = optionalProduct.map(productMapper::toDto).orElse(null);

        ApiResponse<ProductDto> apiResponse = new ApiResponse<>();
        apiResponse.setResult(result);
        apiResponse.setMessage(result != null ? "Lấy sản phẩm thành công" : "Sản phẩm không tồn tại");

        return apiResponse;
    }

    @Override
    public ApiResponse<Boolean> delete(UUID id) {
        ApiResponse<Boolean> apiResponse = new ApiResponse<>();

        if (!productRepository.existsById(id)) {
            apiResponse.setMessage("Cập nhật thất bại");
        }

        productRepository.deleteById(id);

        apiResponse.setResult(true);
        apiResponse.setMessage("Xóa sản phẩm thành công");

        return apiResponse;
    }

}

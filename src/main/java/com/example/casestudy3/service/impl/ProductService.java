package com.example.casestudy3.service.impl;

import com.example.casestudy3.dto.request.ProductDto;
import com.example.casestudy3.dto.response.ApiResponse;
import com.example.casestudy3.entity.Categories;
import com.example.casestudy3.entity.Log;
import com.example.casestudy3.entity.Product;
import com.example.casestudy3.repository.LogRepository;
import com.example.casestudy3.repository.ProductRepository;
import com.example.casestudy3.service.IProductService;
import com.example.casestudy3.tranferDatas.ProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
    private final ProductMapper productMapper = ProductMapper.INSTANCE;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private LogRepository logRepository;

    public void saveLog(String message) {
        Log log = new Log();
        log.setMessage(message);
        logRepository.save(log);
    }

    @Transactional
    public void createProduct() throws Exception {
        System.out.println("------ createProduct ------");
        Product prod = new Product();
        prod.setStatus("This is createProduct method.");
        saveLog("Create Product");
        productRepository.save(prod);

//        try { // VD3 :
//            // VD4:
//            // VD5: Đóng try catch
//            ordersService.createOrder();
//        } catch (RuntimeException e) {
//            System.out.println("Handle " + e.getMessage());
//        }

        ordersService.createOrder(); // VD5
        throw new FileNotFoundException("Nest");
//        throw new RuntimeException("Create Product RuntimeException"); // VD5
    }

    @Override
    public ApiResponse<ProductDto> create(ProductDto productDto) {
//        Product product = productMapper.toEntity(productDto);
        Product product = new Product();
        Categories categories = new Categories();

        categories = productDto.getCategories();
        product.setCategories(categories);
        product = productRepository.save(product);
        ProductDto result = productMapper.toDto(product);

        ApiResponse<ProductDto> apiResponse = new ApiResponse<ProductDto>();
        apiResponse.setResult(result);
        apiResponse.setMessage(result != null ? "Đã tạo sản phẩm thành công" : "Lỗi trong quá trình tạo sản phẩm");

        return apiResponse;
    }

    @Override
    public ApiResponse<ProductDto> update(ProductDto productDto, UUID id) {
        try {
            ApiResponse<ProductDto> apiResponse = new ApiResponse<>();

            if (!productRepository.existsById(id)) {
                apiResponse.setMessage("Cập nhật thất bại");
            }

            Product product = productRepository.findById(id).get();
            if (product != null) {
                productMapper.updateEntityFromDto(productDto, product);
                product = productRepository.save(product);
            }

            ProductDto result = productMapper.toDto(product);

            apiResponse.setResult(result);
            apiResponse.setMessage(result != null ? "Cập nhật sản phẩm thành công" : "Lỗi trong quá trình cập nhật sản phẩm");

            return apiResponse;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
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

    public List<Product> find(String code) {
        List<Product> optionalProduct = productRepository.findByStatus(code);

        return null;
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

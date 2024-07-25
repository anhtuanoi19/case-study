package com.example.casestudy3.service.impl;

import com.example.casestudy3.dto.request.DeliveryDto;
import com.example.casestudy3.dto.response.ApiResponse;
import com.example.casestudy3.entity.Delivery;
import com.example.casestudy3.exception.AppException;
import com.example.casestudy3.exception.ErrorCode;
import com.example.casestudy3.repository.DeliveryRepository;
import com.example.casestudy3.service.IDeliveryService;
import com.example.casestudy3.tranferDatas.TranferDatas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DeliveryService implements IDeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;
    @Override
    public ApiResponse<DeliveryDto> create(DeliveryDto deliveryDto) {
        Delivery delivery = TranferDatas.convertToEntity(deliveryDto);
        delivery = deliveryRepository.save(delivery);
        DeliveryDto result = TranferDatas.convertToDto(delivery);
        ApiResponse<DeliveryDto> apiResponse = new ApiResponse<>();
        if (result != null){
            apiResponse.setResult(result);
            apiResponse.setMessage("Thêm thành công");
        }
        else {
            throw new AppException(ErrorCode.CATEGORY_NOT_EXISTED);
        }
        return apiResponse;
    }

    @Override
    public ApiResponse<DeliveryDto> update(DeliveryDto deliveryDto, UUID id) {
        return null;
    }

    @Override
    public ApiResponse<List<DeliveryDto>> getAll() {
        List<DeliveryDto> result;
        String message;

        try {
            // Lấy tất cả các thực thể từ cơ sở dữ liệu
            List<Delivery> deliveries = deliveryRepository.findAll();

            // Chuyển đổi danh sách thực thể thành danh sách DTO
            result = TranferDatas.convertListDelivery(deliveries);

            // Kiểm tra giá trị null
            if (result == null || result.isEmpty()) {
                message = "Không có dữ liệu nào";
            } else {
                message = "Lấy danh sách thành công";
            }
        } catch (Exception e) {
            result = new ArrayList<>();
            message = "Lỗi trong quá trình lấy dữ liệu: " + e.getMessage();
        }

        // Tạo đối tượng ApiResponse và thiết lập kết quả cùng với thông báo
        ApiResponse<List<DeliveryDto>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(result);
        apiResponse.setMessage(message);

        return apiResponse;
    }

    @Override
    public ApiResponse<DeliveryDto> findById(UUID id) {
        return null;
    }

    @Override
    public ApiResponse<Boolean> delete(UUID id) {
        return null;
    }


}

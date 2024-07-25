package com.example.casestudy3.controller;

import com.example.casestudy3.dto.request.CategoryDto;
import com.example.casestudy3.dto.response.ApiResponse;
import com.example.casestudy3.entity.Categories;
import com.example.casestudy3.exception.AppException;
import com.example.casestudy3.exception.ErrorCode;
import com.example.casestudy3.service.IService;
import com.example.casestudy3.service.impl.CategoryService;
import com.example.casestudy3.tranferDatas.TranferDatas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private IService<Categories> service;
//    private final IServiceHello iServiceHello;
//
//    @Autowired
//    public CategoryController(IServiceHello iServiceHello){
//        this.iServiceHello = iServiceHello;
//    }


    @GetMapping()
    private ApiResponse<List<CategoryDto>> getAll(){
        List<CategoryDto> listDto = TranferDatas.convertListCategory(service.getAll());
        ApiResponse<List<CategoryDto>> apiResponse = new ApiResponse<>();
        if (!listDto.isEmpty()){
            apiResponse.setMessage("Lấy danh sách category thành công");
            apiResponse.setResult(listDto);
        }else {
            throw new AppException(ErrorCode.NO_CATEGORY_FOUND);
        }
        return apiResponse;
    }

    @PostMapping
    private ApiResponse<Categories> create(@RequestBody CategoryDto request){
        ApiResponse<Categories> apiResponse = new ApiResponse<>();
        if (request != null){
            apiResponse.setResult(service.create(TranferDatas.convertToEntity(request)));
        }else {
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }

    return apiResponse;
    }

    @PutMapping("/{id}")
    Categories update(@RequestBody CategoryDto request, @PathVariable String id) {
        UUID idCategories = null;
        if (id != null) idCategories = UUID.fromString(id);
        if (request != null)
            return service.update(TranferDatas.convertToEntity(request), idCategories);
        return null;
    }

    //tìm hiểu required của PathVariable
    @GetMapping({"/findById", "/findById/{id}"})
    public ApiResponse<Categories> findById(@PathVariable(required = false) Optional<UUID> id) {
        ApiResponse<Categories> apiResponse = new ApiResponse<>();
        if (id.isPresent()) {
            apiResponse.setMessage("Đã tìm thấy category");
            apiResponse.setResult(service.findById(id.get()));
        } else {
            apiResponse.setMessage("ID không được cung cấp");
            apiResponse.setResult(null);
        }
        return apiResponse;
    }

    @DeleteMapping("{id}")
    ApiResponse<Categories> delete(@PathVariable UUID id){
        ApiResponse<Categories> apiResponse = new ApiResponse<>();
        if (service.delete(id)){
            apiResponse.setMessage("Xóa thành công");
        }else {
            throw new AppException(ErrorCode.CATEGORY_DELETE);
        }
        return apiResponse;
    }


    @GetMapping("/findByName")
    ApiResponse<List<CategoryDto>> findByName(@RequestParam String name){
        List<CategoryDto> listDto = TranferDatas.convertListCategory(service.findByName(name));
        ApiResponse<List<CategoryDto>> apiResponse = new ApiResponse<>();
        if (!listDto.isEmpty()){
            apiResponse.setMessage("tìm kiếm tên thành oông");
            apiResponse.setResult(listDto);
        }else {
            throw new AppException(ErrorCode.CATEGORY_NOT_EXISTED);
        }
        return apiResponse;
    }

    @Autowired
    private CategoryService categoriesService;

    @GetMapping("/status/{status}")
    public List<Categories> getCategoriesByStatus(@PathVariable Integer status) {
        return categoriesService.getCategoriesByStatus(status);
    }

    @GetMapping("/name/{name}")
    public List<Categories> getCategoriesByName(@PathVariable String name) {
        return categoriesService.getCategoriesByNameUsingEntityManager(name);
    }

    @GetMapping("/status-entitymanager/{status}")
    public List<Categories> getCategoriesByStatusUsingCriteria(@PathVariable Integer status) {
        return categoriesService.getCategoriesByStatusUsingCriteria(status);
    }

    @PostMapping("/status")
    public ApiResponse<Page<CategoryDto>> getCategoriesByStatus(@RequestBody Map<String, Object> request) {
        int page = (int) request.getOrDefault("page", 0);
        int size = (int) request.getOrDefault("size", 5);
        Integer status = (Integer) request.get("status");

        Pageable pageable = PageRequest.of(page, size);
        Page<Categories> categoriesPage = categoriesService.getCategoriesByStatus(status, pageable);
        List<CategoryDto> listDto = TranferDatas.convertListCategory(categoriesPage.getContent());

        ApiResponse<Page<CategoryDto>> apiResponse = new ApiResponse<>();
        if (!listDto.isEmpty()) {
            apiResponse.setMessage("Lấy danh sách categories thành công");
            apiResponse.setResult(new PageImpl<>(listDto, pageable, categoriesPage.getTotalElements()));
        } else {
            throw new AppException(ErrorCode.NO_CATEGORY_FOUND);
        }
        return apiResponse;
    }

}

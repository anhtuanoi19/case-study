package com.example.casestudy3.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "UNCATEGORIZED ERROR", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "INVALID MESSAGE", HttpStatus.BAD_REQUEST),
    NO_CATEGORY_FOUND(1001, "Không có danh sách category nào", HttpStatus.NOT_FOUND),
    CATEGORY_NOT_EXISTED(1002, "Không tìm thấy Category nào", HttpStatus.NOT_FOUND),
    CATEGORY_EXISTED(1003, "Mã danh mục không được trùng", HttpStatus.NOT_FOUND),
    CATEGORY_DELETE(1004, "Xóa thất bại",HttpStatus.BAD_REQUEST),
    DELIVERY_NOT_EXISTED(1005, "Không tìm thấy delivery nào", HttpStatus.NOT_FOUND),
    CUSTOMER_NOT_EXISTED(1006, "Không tìm thấy khách hàng nào", HttpStatus.NOT_FOUND),



    ;

    ErrorCode(int code, String message, HttpStatusCode httpStatusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = httpStatusCode;
    }

    private int code;
    private String message;
    private HttpStatusCode statusCode;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
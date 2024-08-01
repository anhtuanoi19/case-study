package com.example.casestudy3.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNWANTED_EXCEPTION(500, "UNWANTED EXCEPTION", HttpStatus.INTERNAL_SERVER_ERROR),
    UNCATEGORIZED_EXCEPTION(9999, "UNCATEGORIZED ERROR", HttpStatus.INTERNAL_SERVER_ERROR),
    NO_CATEGORY_FOUND(1000, "Không có danh sách category nào", HttpStatus.NOT_FOUND),
    INVALID_KEY(1001, "INVALID MESSAGE", HttpStatus.BAD_REQUEST),
    CATEGORY_NOT_EXISTED(1002, "Không tìm thấy Category nào", HttpStatus.NOT_FOUND),
    CATEGORY_EXISTED(1003, "Mã danh mục không được trùng", HttpStatus.NOT_FOUND),
    CATEGORY_DELETE(1004, "Xóa thất bại", HttpStatus.BAD_REQUEST),
    PRODUCT_DELETE(1005, "Xóa PRODUCT thất bại", HttpStatus.BAD_REQUEST),
    RUNTIME_ERROR(1006, "Lỗi do người dùng nhập chưa đúng dữ liệu, hoặc dữ liệu xung đột", HttpStatus.BAD_REQUEST),
    SQL_ERROR(1007, "Lỗi trong quá trình đưa dữ liệu và cơ sở dữ liệu", HttpStatus.BAD_REQUEST),

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
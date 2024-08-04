package com.example.casestudy3.exception;


import com.example.casestudy3.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    // Quản lý toàn bộ exception

    // Exception
//    @ExceptionHandler(value = Exception.class)
//    ResponseEntity<ApiResponse> handlingUnwantedException(Exception exception) {
//        ApiResponse apiResponse = new ApiResponse();
//        apiResponse.setCode(ErrorCode.UNWANTED_EXCEPTION.getCode());
//        apiResponse.setMessage(ErrorCode.UNWANTED_EXCEPTION.getMessage());
//        return ResponseEntity.internalServerError().body(apiResponse);
//    }

    // RuntimeException
//    @ExceptionHandler(value = RuntimeException.class)
//    ResponseEntity<ApiResponse> handlingRuntimeException(RuntimeException runtimeException) {
//        ApiResponse apiResponse = new ApiResponse();
//        apiResponse.setCode(ErrorCode.RUNTIME_ERROR.getCode());
//        apiResponse.setMessage(ErrorCode.RUNTIME_ERROR.getMessage());
//        return ResponseEntity.badRequest().body(apiResponse);
//    }

    // Custom Exception
    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handlingAppException(AppException appException) {
        ApiResponse apiResponse = new ApiResponse();
        ErrorCode errorCode = appException.getErrorCode();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());
        return ResponseEntity
                .status(errorCode.getStatusCode())
                .body(apiResponse);
    }

    // Custom Exception
    @ExceptionHandler(value = CustomSQLException.class)
    ResponseEntity<ApiResponse> handlingCustomSQLException(CustomSQLException customSQLException) {
        ApiResponse apiResponse = new ApiResponse();
        ErrorCode errorCode = customSQLException.getErrorCodeCustom();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());
        return ResponseEntity
                .status(errorCode.getStatusCode())
                .body(apiResponse);
    }

    // ValidException
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handlingValidation(MethodArgumentNotValidException exception) {
        String enumKey = exception.getFieldError().getDefaultMessage();

        ErrorCode errorCode = ErrorCode.INVALID_KEY;
        try {
            errorCode = ErrorCode.valueOf(enumKey);
        } catch (IllegalArgumentException e) {
            throw e;
        }
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }

}
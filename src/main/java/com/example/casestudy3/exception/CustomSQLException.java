package com.example.casestudy3.exception;

import java.sql.SQLException;

public class CustomSQLException extends SQLException {
    private ErrorCode errorCodeCustom;

    public CustomSQLException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCodeCustom = errorCode;
    }

    public ErrorCode getErrorCodeCustom() {
        return errorCodeCustom;
    }

    public void setErrorCodeCustom(ErrorCode errorCodeCustom) {
        this.errorCodeCustom = errorCodeCustom;
    }
}

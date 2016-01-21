package com.comm;

import java.util.List;

public class ResponseRootClass {
    private int errorCode;
    private String errorMessage;

    public ResponseRootClass() {
        errorCode = 1;
        errorMessage = "传递参数格式不正确";
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

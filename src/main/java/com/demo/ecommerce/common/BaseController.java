package com.demo.ecommerce.common;

public abstract class BaseController {

    protected <T> ApiResponse<T> createSuccessResponse(T data){
        return ApiResponse.success(data);
    }

     protected <T> ApiResponse<T> createErrorResponse(int code, String message){
        return ApiResponse.error(code, message);
    }

     protected <T> ApiResponse<T> createErrorListDataMessages(int code, String message, T data){
        return ApiResponse.errorListDataMessages(code, message, data);
    }

    protected <T> ApiResponse<T> foundResponse(T data){
        return ApiResponse.found(data);
    }
}

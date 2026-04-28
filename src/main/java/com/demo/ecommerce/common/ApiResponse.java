package com.demo.ecommerce.common;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponse<T>(
        int code,
        String message,
        T data
) {

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<T>(201, "Success", data);
    }

    public static <T> ApiResponse<T> error(int code, String message){
        return new ApiResponse<T>(code, message, null);
    }

    public static <T> ApiResponse<T> errorListDataMessages(int code,String message,T data){
        return new ApiResponse<T>(code, message, data);
    }

    public static <T> ApiResponse<T> found(T data){
        return new ApiResponse<T>(200, "Found !!", data);
    }

    public static <T> ApiResponse<T> loginSuccess(T data){
        return new ApiResponse<T>(200, "login successfully", data);
    }
}

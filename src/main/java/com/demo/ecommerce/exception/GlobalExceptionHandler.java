package com.demo.ecommerce.exception;

import com.demo.ecommerce.common.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ApiResponse<Map<String,String>>> handleProductNotFoundException(MethodArgumentNotValidException ex){

        Map<String,String> errorResponse = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach((error)->{
           errorResponse.put(error.getField(),error.getDefaultMessage());
        });

        ApiResponse<Map<String,String>> body = ApiResponse.errorListDataMessages(400, "List of errors",errorResponse);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}

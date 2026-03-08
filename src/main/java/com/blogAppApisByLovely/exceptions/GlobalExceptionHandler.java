package com.blogAppApisByLovely.exceptions;

import com.blogAppApisByLovely.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse>
                      resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
String message = ex.getMessage();
ApiResponse apiResponse = new ApiResponse( message,false);
return new ResponseEntity<ApiResponse>( apiResponse,HttpStatus.NOT_FOUND);
    }
    //for validation exception
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgNotValidException(MethodArgumentNotValidException ex){
       Map<String,String> resp = new HashMap<>();
       ex.getBindingResult().getAllErrors().forEach((error)->{
           String fildName = ((FieldError)error).getField();
           String meassage= error.getDefaultMessage();
           resp.put(fildName,meassage);

       });
       return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
    }


}
package com.dy.common.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException{
    private String message;
    HttpStatus httpStatus;
    public CustomException(String message){
        this.message = message;
    }


    public CustomException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}

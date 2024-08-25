package com.design.patterns.handler;

public class ValidationException extends RuntimeException{
    public  ValidationException(String message){
        super(message);
    }
}

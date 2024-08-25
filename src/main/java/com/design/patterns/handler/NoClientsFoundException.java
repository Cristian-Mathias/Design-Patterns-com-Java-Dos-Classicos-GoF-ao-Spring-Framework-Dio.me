package com.design.patterns.handler;

public class NoClientsFoundException extends RuntimeException{
    public NoClientsFoundException(String message) {
        super(message);
    }
}

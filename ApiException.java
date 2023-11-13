package com.example.placementcell.Exception;

public class ApiException extends RuntimeException{
    public ApiException(String message) {
        super(message);
    }

    public ApiException() {

    }
}

package com.dng.cs.core.exception;

public class InvalidReqBodyException extends RuntimeException{
    public InvalidReqBodyException(String message) {
        super(message);
    }
}

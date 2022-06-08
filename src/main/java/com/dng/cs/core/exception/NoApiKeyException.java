package com.dng.cs.core.exception;

public class NoApiKeyException extends RuntimeException{
    public NoApiKeyException(String message) {
        super(message);
    }
}

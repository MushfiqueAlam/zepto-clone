package com.central_api.exception;

public class ProductNotPresentException extends Throwable {
    public ProductNotPresentException(String message) {
        super(message);
    }
}

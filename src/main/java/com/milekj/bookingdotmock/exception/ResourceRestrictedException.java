package com.milekj.bookingdotmock.exception;

public class ResourceRestrictedException extends RuntimeException {
    public ResourceRestrictedException(String message) {
        super(message);
    }
}

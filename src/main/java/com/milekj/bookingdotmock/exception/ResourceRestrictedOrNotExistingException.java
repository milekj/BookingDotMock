package com.milekj.bookingdotmock.exception;

public class ResourceRestrictedOrNotExistingException extends RuntimeException {
    public ResourceRestrictedOrNotExistingException(String message) {
        super(message);
    }
}

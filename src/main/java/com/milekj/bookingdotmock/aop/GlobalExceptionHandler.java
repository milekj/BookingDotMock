package com.milekj.bookingdotmock.aop;

import com.milekj.bookingdotmock.exception.ResourceRestrictedOrNotExistingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceRestrictedOrNotExistingException.class)
    public String handleSecurityException() {
        return "/errors/resource-restricted.html";
    }
}

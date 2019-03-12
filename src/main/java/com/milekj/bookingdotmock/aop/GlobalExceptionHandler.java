package com.milekj.bookingdotmock.aop;

import com.milekj.bookingdotmock.exception.ResourceRestrictedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceRestrictedException.class)
    public String handleSecurityException() {
        return "/errors/resource-restricted.html";
    }
}

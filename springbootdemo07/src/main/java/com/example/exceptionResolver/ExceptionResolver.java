package com.example.exceptionResolver;


import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionResolver {


    @ExceptionHandler(RuntimeException.class)
    public String handleException(RuntimeException e) {
        if (e instanceof AccessDeniedException) {
            return "redirect:/403.jsp";
        }

        return "redirect:/500.jsp";
    }
}

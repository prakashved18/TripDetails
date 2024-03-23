package com.demo.TripDetails.advice;

import com.demo.TripDetails.constants.PurchaseTicketConstants;
import com.demo.TripDetails.exception.UserTicketNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class PurchaseTicketExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserTicketNotFoundException.class)
    public Map<String, String> handleCustomerNotFoundException(UserTicketNotFoundException userTicketNotFoundException){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put(PurchaseTicketConstants.ERROR_KEY, userTicketNotFoundException.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleArgumentNotValidException(MethodArgumentNotValidException argumentNotValidException){
        Map<String, String> errorMap = new HashMap<>();
        argumentNotValidException.getBindingResult().getFieldErrors().forEach(
                error -> errorMap.put(error.getField(), error.getDefaultMessage())
        );
        return errorMap;
    }
}

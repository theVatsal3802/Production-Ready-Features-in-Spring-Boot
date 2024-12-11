package com.vatsaladhiya.vatsal.prodReadyFeatures.prodReadyFeatures.advices;

import com.vatsaladhiya.vatsal.prodReadyFeatures.prodReadyFeatures.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIError> handleResourceNotFoundException(ResourceNotFoundException exception) {
        APIError error = new APIError(exception.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}

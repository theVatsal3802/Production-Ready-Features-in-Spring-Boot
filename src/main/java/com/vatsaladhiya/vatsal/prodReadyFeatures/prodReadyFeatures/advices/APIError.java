package com.vatsaladhiya.vatsal.prodReadyFeatures.prodReadyFeatures.advices;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class APIError {
    private LocalDateTime timeStamp;
    private String message;
    private HttpStatus statusCode;

    public APIError() {
        this.timeStamp = LocalDateTime.now();
    }

    public APIError(String message, HttpStatus statusCode) {
        this();
        this.message = message;
        this.statusCode = statusCode;
    }
}

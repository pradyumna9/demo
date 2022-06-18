package com.example.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


@Getter
@Builder
@AllArgsConstructor
public class ErrorResponse {
    private String errorCode;
    private String errorDescription;
    private HttpStatus httpStatus;
    private LocalDateTime timeStamp;
}

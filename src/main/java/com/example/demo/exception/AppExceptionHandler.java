package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException constraintViolationException){
        InternalStandardErrors internalStandardErrors = InternalStandardErrors.MOBILE_NUMBER_INVALID;

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.builder().httpStatus(internalStandardErrors.getStatus()).errorCode(internalStandardErrors.getErrorCode())
                .errorDescription(internalStandardErrors.getErrorDescription()).timeStamp(LocalDateTime.now()).build());
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(AppException exception){

        InternalStandardErrors standardErrors = exception.getErrors();
        return  ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.builder().errorCode(standardErrors.getErrorCode())
                        .errorDescription(standardErrors.getErrorDescription())
                        .httpStatus(standardErrors.getStatus())
                        .timeStamp(LocalDateTime.now()).build());
    }

    @ExceptionHandler(PropertyNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(PropertyNotFoundException propertyNotFoundException){
        InternalStandardErrors standardErrors = propertyNotFoundException.getErrors();
        return  ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.builder().errorCode(standardErrors.getErrorCode())
                        .errorDescription(standardErrors.getErrorDescription())
                        .httpStatus(standardErrors.getStatus())
                        .timeStamp(LocalDateTime.now()).build());
    }
}

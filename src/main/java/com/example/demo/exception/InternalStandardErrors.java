package com.example.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum InternalStandardErrors {
        MOBILE_NUMBER_NOT_EXIST("EC012","Mobile Number Not Exist", HttpStatus.NOT_FOUND),
        MOBILE_NUMBER_INVALID("EC013","Mobile Number Should be  10 digit", HttpStatus.BAD_REQUEST),
        PROPERTY_NOT_FOUND("EC014","Property Not Available", HttpStatus.NOT_FOUND);
        private String errorCode;
        private String errorDescription;
        private HttpStatus status;
    }

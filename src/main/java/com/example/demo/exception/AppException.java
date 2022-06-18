package com.example.demo.exception;

import lombok.Getter;

@Getter
public class AppException extends RuntimeException{

 private InternalStandardErrors errors;

    public AppException(InternalStandardErrors errors) {
        super(errors.getErrorDescription());
        this.errors = errors;
    }
}

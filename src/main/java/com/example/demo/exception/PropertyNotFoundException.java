package com.example.demo.exception;

import lombok.Getter;

@Getter
public class PropertyNotFoundException extends  RuntimeException{

    private InternalStandardErrors errors;

    public PropertyNotFoundException(InternalStandardErrors errors) {
        super(errors.getErrorDescription());
        this.errors = errors;
    }
}

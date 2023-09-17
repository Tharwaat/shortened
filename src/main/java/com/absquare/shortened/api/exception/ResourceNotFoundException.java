package com.absquare.shortened.api.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ResourceNotFoundException extends Exception {
    private final String resourceValue;

    public ResourceNotFoundException(String resourceValue) {
        this.resourceValue = resourceValue;
    }
}

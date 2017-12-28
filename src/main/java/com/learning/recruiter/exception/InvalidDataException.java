package com.learning.recruiter.exception;

import com.learning.recruiter.api.ErrorType;
import lombok.Getter;

import java.util.ResourceBundle;

@Getter
public class InvalidDataException extends RuntimeException {

    private ErrorType errorType;
    private String message;
    private final Object[] arguments;

    public InvalidDataException(ErrorType errorType, Object... arguments) {
        this.errorType = errorType;
        this.arguments = arguments;
        this.message = String.format(
                ResourceBundle.getBundle("errors").getString(errorType.getValue()),
                arguments);
    }

    public InvalidDataException(ErrorType errorType) {
        this.errorType = errorType;
        this.arguments = new Object[0];
        this.message = ResourceBundle.getBundle("errors").getString(errorType.getValue());
    }
}

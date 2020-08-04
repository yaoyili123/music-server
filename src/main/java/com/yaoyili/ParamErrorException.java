package com.yaoyili;

public class ParamErrorException extends RuntimeException {

    public ParamErrorException() {
        super();
    }

    public ParamErrorException(String message) {
        super(message);
    }
}

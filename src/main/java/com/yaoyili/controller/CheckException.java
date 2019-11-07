package com.yaoyili.controller;

/*
* 需要服务端提示的错误
* */
public class CheckException extends RuntimeException {

    public CheckException() {
        super();
    }

    public CheckException(String message) {
        super(message);
    }
}

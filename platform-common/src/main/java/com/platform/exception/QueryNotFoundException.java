package com.platform.exception;

public class QueryNotFoundException extends BaseException{
    public QueryNotFoundException() {
    }

    public QueryNotFoundException(String msg) {
        super(msg);
    }
}

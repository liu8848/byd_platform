package com.platform.exception;

public class ProcessNotExistException extends BaseException {
    public ProcessNotExistException() {
    }

    public ProcessNotExistException(String msg) {
        super(msg);
    }
}

package com.puzzle.api.exception;

public class UnknownException extends ApiException {
    private final static String exceptionCode = ExceptionCode.Api.UNKNOWN_EXCEPTION;

    UnknownException() {
        super(exceptionCode);
    }

    public UnknownException(String message) {
        super(exceptionCode + "  , message");
    }
}

package com.puzzle.iam.domain.exceptions;

import com.puzzle.api.exception.ApiException;
import com.puzzle.api.exception.ExceptionCode;

public class NotValidPasswordException extends ApiException {
    private final static String exceptionCode = ExceptionCode.User.NOT_VALID_PASSWORD_EXCEPTION;

    public NotValidPasswordException() {
        super(exceptionCode);
    }
}

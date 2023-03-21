package com.puzzle.iam.domain.exceptions;

import com.puzzle.api.exception.ApiException;
import com.puzzle.api.exception.ExceptionCode;

public class UserNotFoundException extends ApiException {
    private final static String exceptionCode = ExceptionCode.User.USER_NOT_FOUND_EXCEPTION;

    public UserNotFoundException() {
        super(exceptionCode);
    }

    public UserNotFoundException(final String var) {
        super(exceptionCode + ": " + var);
    }
}

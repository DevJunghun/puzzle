package com.puzzle.iam.domain.exceptions;

import com.puzzle.api.exception.ApiException;
import com.puzzle.api.exception.ExceptionCode;

public class UserTokenNotFoundException extends ApiException {
    private final static String exceptionCode = ExceptionCode.UserToken.USER_TOKEN_NOT_FOUND_EXCEPTION;

    public UserTokenNotFoundException() {
        super(exceptionCode);
    }

    public UserTokenNotFoundException(final String var) {
        super(exceptionCode + ": " + var);
    }
}

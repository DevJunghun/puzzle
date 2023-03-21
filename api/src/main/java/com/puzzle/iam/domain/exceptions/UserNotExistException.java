package com.puzzle.iam.domain.exceptions;

import com.puzzle.api.exception.ApiException;
import com.puzzle.api.exception.ExceptionCode;

public class UserNotExistException extends ApiException {
    private final static String exceptionCode = ExceptionCode.User.USER_NOT_EXIST_EXCEPTION;

    public UserNotExistException(final String username) {
        super(exceptionCode + ": " + username);
    }
}

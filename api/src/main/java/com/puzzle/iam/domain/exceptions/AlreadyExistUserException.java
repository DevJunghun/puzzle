package com.puzzle.iam.domain.exceptions;

import com.puzzle.api.exception.ApiException;
import com.puzzle.api.exception.ExceptionCode;

public class AlreadyExistUserException extends ApiException {
    private final static String exceptionCode = ExceptionCode.User.ALREADY_EXIST_USER_EXCEPTION;

    public AlreadyExistUserException(final String username) {
        super(exceptionCode + ": " + username);
    }
}

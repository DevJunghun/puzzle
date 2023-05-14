package com.puzzle.iam.domain.exceptions;

import com.puzzle.api.exception.ApiException;
import com.puzzle.api.exception.ExceptionCode;

public class UserLockedException extends ApiException {
    private final static String exceptionCode = ExceptionCode.User.USER_LOCKED_EXCEPTION;

    public UserLockedException(final String userUuid) {
        super(exceptionCode + ": " + userUuid);
    }
}

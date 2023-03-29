package com.puzzle.iam.domain.exceptions;

import com.puzzle.api.exception.ApiException;
import com.puzzle.api.exception.ExceptionCode;

public class AlreadyExistUserEmailException extends ApiException {
    private final static String exceptionCode = ExceptionCode.User.ALREADY_EXIST_USER_EXCEPTION;

    public AlreadyExistUserEmailException(final String email) {
        super(exceptionCode + ": " + email);
    }
}

package com.puzzle.email.domain.exception;

import com.puzzle.api.exception.ApiException;
import com.puzzle.api.exception.ExceptionCode;

public class UserEmailAlreadyExistException extends ApiException {
    private final static String exceptionCode = ExceptionCode.UserEmail.USER_EMAIL_ALREADY_EXIST_EXCEPTION;

    public UserEmailAlreadyExistException(final String email) {
        super(exceptionCode + ": " + email);
    }
}

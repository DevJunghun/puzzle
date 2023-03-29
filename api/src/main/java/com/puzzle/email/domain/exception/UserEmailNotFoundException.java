package com.puzzle.email.domain.exception;

import com.puzzle.api.exception.ApiException;
import com.puzzle.api.exception.ExceptionCode;

public class UserEmailNotFoundException extends ApiException {
    private final static String exceptionCode = ExceptionCode.UserEmail.USER_EMAIL_ALREADY_EXIST_EXCEPTION;

    public UserEmailNotFoundException(final String email) {
        super(exceptionCode + ": " + email);
    }
}

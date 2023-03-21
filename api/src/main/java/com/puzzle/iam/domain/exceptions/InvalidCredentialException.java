package com.puzzle.iam.domain.exceptions;

import com.puzzle.api.exception.ApiException;
import com.puzzle.api.exception.ExceptionCode;

public class InvalidCredentialException extends ApiException {
    private final static String exceptionCode = ExceptionCode.User.INVALID_CREDENTIAL_EXCEPTION;

    public InvalidCredentialException() {
        super(exceptionCode);
    }
}

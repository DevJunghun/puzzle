package com.puzzle.address.domain.exceptions;

import com.puzzle.api.exception.ApiException;
import com.puzzle.api.exception.ExceptionCode;

public class AddressNotFound extends ApiException {
    private final static String exceptionCode = ExceptionCode.Address.ADDRESS_NOT_FOUND_EXCEPTION;

    public AddressNotFound(final String uuid) {
        super(exceptionCode + ": " + uuid);
    }
}

package com.puzzle.address.domain.exceptions;

import com.puzzle.api.exception.ApiException;
import com.puzzle.api.exception.ExceptionCode;

public class AddressGroupNotFound extends ApiException {
    private final static String exceptionCode = ExceptionCode.AddressGroup.ADDRESS_GROUP_NOT_FOUND_EXCEPTION;

    public AddressGroupNotFound(final String uuid) {
        super(exceptionCode + ": " + uuid);
    }
}

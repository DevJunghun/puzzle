package com.puzzle.address.domain.exceptions;

import com.puzzle.api.exception.ApiException;
import com.puzzle.api.exception.ExceptionCode;

public class DefaultAddressGroupCanNotBeUpdateException extends ApiException {
    private final static String exceptionCode = ExceptionCode.AddressGroup.DEFAULT_ADDRESS_GROUP_CAN_NOT_BE_UPDATE_EXCEPTION;

    public DefaultAddressGroupCanNotBeUpdateException(final String uuid) {
        super(exceptionCode + ": " + uuid);
    }
}

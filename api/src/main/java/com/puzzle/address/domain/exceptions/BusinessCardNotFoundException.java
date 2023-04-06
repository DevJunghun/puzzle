package com.puzzle.address.domain.exceptions;

import com.puzzle.api.exception.ApiException;
import com.puzzle.api.exception.ExceptionCode;

public class BusinessCardNotFoundException extends ApiException {
    private final static String exceptionCode = ExceptionCode.BusinessCard.BUSINESS_CARD_NOT_FOUND_EXCEPTION;

    public BusinessCardNotFoundException(final String uuid) {
        super(exceptionCode + ": " + uuid);
    }
}

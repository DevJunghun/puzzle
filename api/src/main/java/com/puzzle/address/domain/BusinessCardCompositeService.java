package com.puzzle.address.domain;

import com.puzzle.api.util.BooleanDelete;
import com.puzzle.api.util.BooleanValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BusinessCardCompositeService {
    private final BusinessCardService service;

    public BusinessCard find(final String uuid) {
        return service.find(uuid, BooleanDelete.FALSE, BooleanValidate.TRUE);
    }
}

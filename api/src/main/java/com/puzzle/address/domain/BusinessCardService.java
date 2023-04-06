package com.puzzle.address.domain;

import com.puzzle.address.domain.exceptions.BusinessCardNotFoundException;
import com.puzzle.api.exception.ExceptionCode;
import com.puzzle.api.util.BooleanDelete;
import com.puzzle.api.util.BooleanValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BusinessCardService {
    private final BusinessCardRepository repository;

    public BusinessCard find(final String uuid, final BooleanDelete deleted, final BooleanValidate validate){
        BusinessCard businessCard;

        if (deleted == BooleanDelete.FALSE) {
            businessCard = repository.findByUuidAndDeletedIsFalse(uuid);
        } else {
            businessCard = repository.findByUuid(uuid);
        }

        validate(uuid, businessCard, validate);

        return businessCard;
    }

    public void delete(final BusinessCard businessCard) {
        businessCard.setUpdatedAt(LocalDateTime.now());
        businessCard.setDeleted(true);

        repository.save(businessCard);
    }

    public void update(final BusinessCard businessCard) {
        businessCard.setUpdatedAt(LocalDateTime.now());

        repository.save(businessCard);
    }

    private void validate(final String uuid, final BusinessCard businessCard, final BooleanValidate validate) {
        if (businessCard == null && validate.isTrue()) {
            throw new BusinessCardNotFoundException(uuid);
        }
    }
}

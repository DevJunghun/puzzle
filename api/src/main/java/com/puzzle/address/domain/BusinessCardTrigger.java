package com.puzzle.address.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BusinessCardTrigger {
    private final AddressCompositeService addressCompositeService;
    public void beforeDelete(final BusinessCard businessCard) {
        addressCompositeService.deleteBusinessCard(businessCard.getAddressUuid());
    }
}

package com.puzzle.address.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressTrigger {
    private final BusinessCardCompositeService businessCardCompositeService;

    public void deleteBusinessCard(final Address address) {
        businessCardCompositeService.deleteByAddress(address.getUuid());
    }
}

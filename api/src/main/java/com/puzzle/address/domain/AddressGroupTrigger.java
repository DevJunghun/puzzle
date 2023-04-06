package com.puzzle.address.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressGroupTrigger {
    private final AddressCompositeService addressCompositeService;

    public void deleteAllAddress(final AddressGroup addressGroup) {
        final var addressList = addressCompositeService.findAll(addressGroup.getUuid());

        addressList
                .forEach(address -> {
                    addressCompositeService.delete(address.getUuid());
                });
    }
}

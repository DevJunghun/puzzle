package com.puzzle.iam.domain;

import com.puzzle.address.domain.AddressGroupCompositeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserTrigger {
    private final AddressGroupCompositeService addressGroupCompositeService;

    public void beforeCreateUser(final User user) {
        addressGroupCompositeService.createDefaultGroup(user.getUuid());
    }
}

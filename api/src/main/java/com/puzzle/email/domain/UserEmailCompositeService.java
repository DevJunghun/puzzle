package com.puzzle.email.domain;

import com.puzzle.api.util.BooleanDelete;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserEmailCompositeService {
    private final UserEmailService service;

    public boolean findEmailExists(final String email) {
        return service.findEmailExists(email, BooleanDelete.TRUE);
    }
}

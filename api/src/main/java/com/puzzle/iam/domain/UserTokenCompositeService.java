package com.puzzle.iam.domain;

import com.puzzle.api.util.BooleanValidate;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserTokenCompositeService {
    private final UserTokenService service;

    @Transactional
    public UserToken find(final String userToken) {
        return service.find(userToken, BooleanValidate.TRUE);
    }

    @Transactional
    public UserToken login(final String userUuid) {
        final var userToken = service.findByUserUuid(userUuid, BooleanValidate.FALSE);

        if (userToken == null) {
            return service.create(userUuid);
        } else {
            return service.update(userToken);
        }
    }
}

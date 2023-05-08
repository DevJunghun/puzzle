package com.puzzle.iam.domain;

import com.puzzle.api.util.BooleanValidate;
import com.puzzle.iam.domain.exceptions.UserTokenNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserTokenService {
    private final UserTokenRepository repository;

    public UserToken find(final String token, final BooleanValidate validate) {
        final var userToken =  repository.findByUserToken(token);

        if (validate.isTrue() && userToken == null) {
            throw new UserTokenNotFoundException(token);
        }

        return userToken;
    }

    public UserToken create(final String userUuid) {
        final var userToken = new UserToken();

        userToken.setUserUuid(userUuid);

        return repository.save(userToken);
    }

    public UserToken update(final UserToken userToken) {
        userToken.setUserToken(UUID.randomUUID().toString());

        return repository.save(userToken);
    }


}

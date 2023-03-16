package com.puzzle.iam.domain;

import com.puzzle.api.exception.ApiException;
import com.puzzle.iam.controller.dto.SignInDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
@RequiredArgsConstructor
public class UserCompositeService {
    private final UserService service;

    private static final String PWD_REGEX = "(\\d+\\w+)|(\\w+\\d)";

    public String signIn(final SignInDto.Request request) {
        validPwdRegex(request.getPwd());

        final var user = createUser(request);
        return service.create(user);
    }

    private void validPwdRegex(final String pwd) {
        if (pwd.matches(PWD_REGEX)) {
            return;

        } else {
            throw new ApiException("regex");
        }
    }

    private Users createUser(final SignInDto.Request request) {
        final var user = new Users();
        final var encodedPwd = new BCryptPasswordEncoder().encode(request.getPwd());

        user.setUsername(request.getUsername());
        user.setPassword(encodedPwd);
        user.setDeleted(false);

        return user;
    }

}

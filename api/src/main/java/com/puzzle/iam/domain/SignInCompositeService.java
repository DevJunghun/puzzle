package com.puzzle.iam.domain;

import com.puzzle.api.exception.ApiException;
import com.puzzle.iam.controller.dto.SignInDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignInCompositeService {
    private final SignInService service;

    private static String PWD_REGEX = "(\\d+\\w+)|(\\w+\\d)";
    public String signIn(final SignInDto.Request request) {
        validPwdRegex(request.getPwd());

        final var user = createUser(request);
        service.create(user);
        return "";
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

        user.setUsername(request.getUsername());
        user.setPassword(request.getPwd());

        return user;
    }

}

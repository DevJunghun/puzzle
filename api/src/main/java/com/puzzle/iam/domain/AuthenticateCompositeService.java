package com.puzzle.iam.domain;

import com.puzzle.api.util.BooleanDelete;
import com.puzzle.api.util.BooleanValidate;
import com.puzzle.iam.controller.dto.AuthenticateDto;
import com.puzzle.iam.domain.exceptions.InvalidCredentialException;
import com.puzzle.iam.domain.exceptions.UserNotExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticateCompositeService {
    private final UserCompositeService userCompositeService;
    private final BCryptPasswordEncoder encoder;

    public AuthenticateDto.LogIn.Response authenticate(final AuthenticateDto.LogIn.Request request) {
        final var user = userCompositeService.findByUsername(request.getUsername(), BooleanDelete.FALSE, BooleanValidate.FALSE);
        if (user == null) {
            throw new UserNotExistException(request.getUsername());
        }

        //todo: userauth wrong id

        if (!encoder.matches(user.getPassword(), request.getPassword())) {
            //todo: userauth wrong pwd
            //todo: if wrong many times?
            throw new InvalidCredentialException();
        }

        return new AuthenticateDto.LogIn.Response("token");
    }

    public void logOut(final String userToken) {
        //get userUuid from token table
        final var userUuid = "userUuid";
        final var user = userCompositeService.findByUuid(userUuid);

        //todo: userauth logout
    }
}

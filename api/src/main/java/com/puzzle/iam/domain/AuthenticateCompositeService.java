package com.puzzle.iam.domain;

import com.puzzle.api.exception.ExceptionCode;
import com.puzzle.api.util.BooleanDelete;
import com.puzzle.api.util.BooleanValidate;
import com.puzzle.iam.controller.dto.AuthenticateDto;
import com.puzzle.iam.domain.exceptions.InvalidCredentialException;
import com.puzzle.iam.domain.exceptions.UserNotExistException;
import com.puzzle.iam.type.AuthType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticateCompositeService {
    private final UserCompositeService userCompositeService;
    private final AuthLogCompositeService authLogCompositeService;
    private final BCryptPasswordEncoder encoder;

    public AuthenticateDto.LogIn.Response authenticate(final AuthenticateDto.LogIn.Request request, final String ip) {
        final var user = userCompositeService.findByUsername(request.getUsername(), BooleanDelete.FALSE, BooleanValidate.FALSE);
        if (user == null) {
            throw new UserNotExistException(request.getUsername());
        }

        authLogCompositeService.create(null, AuthType.LOGIN, false, ip, ExceptionCode.User.USER_NOT_EXIST_EXCEPTION);

        if (!encoder.matches(user.getPassword(), request.getPassword())) {
            authLogCompositeService.create(user.getUuid(), AuthType.LOGIN, false, ip, ExceptionCode.User.INVALID_CREDENTIAL_EXCEPTION);
            //todo: if wrong many times?
            throw new InvalidCredentialException();
        }

        authLogCompositeService.create(user.getUuid(), AuthType.LOGIN, true, ip, null);
        return new AuthenticateDto.LogIn.Response("token");
    }

    public void logOut(final String userToken, final String ip) {
        //get userUuid from token table
        final var userUuid = "userUuid";
        final var user = userCompositeService.findByUuid(userUuid);

        authLogCompositeService.create(user.getUuid(), AuthType.LOGOUT, true, ip, null);
    }
}

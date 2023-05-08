package com.puzzle.iam.domain;

import com.puzzle.api.exception.ExceptionCode;
import com.puzzle.api.util.BooleanDelete;
import com.puzzle.api.util.BooleanValidate;
import com.puzzle.iam.controller.dto.AuthenticateDto;
import com.puzzle.iam.domain.exceptions.InvalidCredentialException;
import com.puzzle.iam.domain.exceptions.UserNotExistException;
import com.puzzle.iam.type.AuthType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticateCompositeService {
    private final UserCompositeService userCompositeService;
    private final AuthLogCompositeService authLogCompositeService;
    private final BCryptPasswordEncoder encoder;

    public String authenticate(final AuthenticateDto.LogIn.Request request, final String ip) {
        final var user = findUserAndValidate(request, ip);

        validCredential(user, request, ip);

        authLogCompositeService.create(user.getUuid(), AuthType.LOGIN, true, ip, null);

        return user.getUuid();
    }

    public void logOut(final User user, final String ip) {
        authLogCompositeService.create(user.getUuid(), AuthType.LOGOUT, true, ip, null);
    }

    private User findUserAndValidate(final AuthenticateDto.LogIn.Request request, final String ip) {
        final var user = userCompositeService.findByUsername(request.getUsername(), BooleanDelete.FALSE, BooleanValidate.FALSE);

        if (user == null) {
            authLogCompositeService.create(null, AuthType.LOGIN, false, ip, ExceptionCode.User.USER_NOT_EXIST_EXCEPTION);
            throw new UserNotExistException(request.getUsername());
        }

        return user;
    }

    private void validCredential(final User user, final AuthenticateDto.LogIn.Request request, final String ip) {
        if (!encoder.matches(user.getPassword(), request.getPassword())) {
            authLogCompositeService.create(user.getUuid(), AuthType.LOGIN, false, ip, ExceptionCode.User.INVALID_CREDENTIAL_EXCEPTION);
            //todo: if wrong many times?
            throw new InvalidCredentialException();
        }
    }
}

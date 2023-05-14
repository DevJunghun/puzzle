package com.puzzle.iam.domain;

import com.puzzle.api.exception.ExceptionCode;
import com.puzzle.api.util.BooleanDelete;
import com.puzzle.api.util.BooleanValidate;
import com.puzzle.iam.controller.dto.AuthenticateDto;
import com.puzzle.iam.domain.exceptions.InvalidCredentialException;
import com.puzzle.iam.domain.exceptions.UserLockedException;
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

    public String authenticate(final AuthenticateDto.LogIn.Request request, final String ip) {
        final var user = findUserAndValidate(request, ip);

        validLocked(user, ip);
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
            if (getFailTime(user) >= 5) {
                user.setLocked(true);
                userCompositeService.lock(user);

                authLogCompositeService.create(user.getUuid(), AuthType.LOCKED, true, ip, null);
            } else {
                authLogCompositeService.create(user.getUuid(), AuthType.LOGIN, false, ip, ExceptionCode.User.INVALID_CREDENTIAL_EXCEPTION);
            }

            throw new InvalidCredentialException();
        }
    }

    private int getFailTime(final User user) {
        return authLogCompositeService.failTimeCount(user) + 1;
    }

    private void validLocked(final User user, final String ip) {
        if (user.isLocked()) {
            authLogCompositeService.create(user.getUuid(), AuthType.LOGIN, false, ip, ExceptionCode.User.USER_LOCKED_EXCEPTION);
            throw new UserLockedException(user.getUsername());
        }
    }
}

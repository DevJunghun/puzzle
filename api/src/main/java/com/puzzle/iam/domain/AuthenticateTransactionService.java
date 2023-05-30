package com.puzzle.iam.domain;

import com.puzzle.iam.controller.dto.AuthenticateDto;
import com.puzzle.iam.domain.exceptions.InvalidCredentialException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthenticateTransactionService {
    private final UserTokenCompositeService userTokenCompositeService;
    private final AuthenticateCompositeService compositeService;

    @Transactional(noRollbackFor = InvalidCredentialException.class)
    public AuthenticateDto.LogIn.Response authenticate(final AuthenticateDto.LogIn.Request request, final String ip) {
        final var userUuid = compositeService.authenticate(request, ip);

        final var userToken = userTokenCompositeService.login(userUuid);

        return new AuthenticateDto.LogIn.Response(userToken.getUserToken());
    }
}

package com.puzzle.iam.domain;

import com.puzzle.iam.controller.dto.AuthenticateDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticateTransactionService {
    private final UserTokenCompositeService userTokenCompositeService;
    private final AuthenticateCompositeService compositeService;

    @Transactional
    public AuthenticateDto.LogIn.Response authenticate(final AuthenticateDto.LogIn.Request request, final String ip) {
        final var userUuid = compositeService.authenticate(request, ip);

        final var userToken = userTokenCompositeService.login(userUuid);

        return new AuthenticateDto.LogIn.Response(userToken.getUserToken());
    }
}

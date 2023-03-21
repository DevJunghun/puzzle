package com.puzzle.iam.controller;

import com.puzzle.iam.controller.dto.AuthenticateDto;
import com.puzzle.iam.domain.AuthenticateCompositeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.puzzle.api.session.UserTokenHeader.HEADER_KEY;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/authenticate", produces = {MediaType.APPLICATION_JSON_VALUE})
@Slf4j
public class AuthenticateController {
    private final AuthenticateCompositeService compositeService;
    @PostMapping("/auth")
    public AuthenticateDto.LogIn.Response authenticate(@RequestBody AuthenticateDto.LogIn.Request request, final HttpServletRequest httpServletRequest) {
        final var ip = httpServletRequest.getRemoteAddr();

        return compositeService.authenticate(request, ip);
    }

    @GetMapping("/logout")
    public void logout(@RequestHeader(HEADER_KEY) String userToken, final HttpServletRequest httpServletRequest) {
        final var ip = httpServletRequest.getRemoteAddr();

        compositeService.logOut(userToken, ip);
    }
}

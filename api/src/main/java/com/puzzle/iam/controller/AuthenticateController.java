package com.puzzle.iam.controller;

import com.puzzle.iam.controller.dto.AuthenticateDto;
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
    @PostMapping("/kakao")
    public AuthenticateDto.Response authenticate(@RequestBody AuthenticateDto.Kakao request) {
        return new AuthenticateDto.Response("username", "token");
    }

    @PostMapping("/google")
    public AuthenticateDto.Response authenticate(@RequestBody AuthenticateDto.Google request) {
        return new AuthenticateDto.Response("username", "token");
    }

    @PostMapping("/naver")
    public AuthenticateDto.Response authenticate(@RequestBody AuthenticateDto.Naver request) {
        return new AuthenticateDto.Response("username", "token");
    }

    @GetMapping("/logout")
    public void logout(@RequestHeader(HEADER_KEY) String userToken) {
    }
}

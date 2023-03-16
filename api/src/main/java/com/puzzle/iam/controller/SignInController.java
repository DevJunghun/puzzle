package com.puzzle.iam.controller;

import com.puzzle.iam.controller.dto.SignInDto;
import com.puzzle.iam.domain.SignInCompositeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/authenticate", produces = {MediaType.APPLICATION_JSON_VALUE})
@Slf4j
public class SignInController {
    private final SignInCompositeService compositeService;
    @PutMapping("/sign-in")
    public SignInDto.Response signIn(final @Valid @RequestBody SignInDto.Request request) {
        compositeService.signIn(request);
        return null;
    }

}
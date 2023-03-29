package com.puzzle.email.controller;

import com.puzzle.email.domain.UserEmailCompositeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user-email", produces = {MediaType.APPLICATION_JSON_VALUE})
@Slf4j
public class UserEmailController {
    private final UserEmailCompositeService compositeService;
}

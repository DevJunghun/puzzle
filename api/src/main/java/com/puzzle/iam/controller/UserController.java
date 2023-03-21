package com.puzzle.iam.controller;

import com.puzzle.iam.controller.dto.SignInDto;
import com.puzzle.iam.domain.UserCompositeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user", produces = {MediaType.APPLICATION_JSON_VALUE})
@Slf4j
public class UserController {
    private final UserCompositeService compositeService;

    @PutMapping("/sign-in")
    public SignInDto.Create.Response signIn(final @Valid @RequestBody SignInDto.Create.Request request) {
        return compositeService.signIn(request);
    }

    @GetMapping("/lost-username/{email}")
    public void lostUsername(final @PathVariable String email) {
        compositeService.findUsername(email);
    }

    @GetMapping("/lost-password/{email}")
    public void lostPassword(final @PathVariable String email) {
        compositeService.findPassword(email);
    }

    @DeleteMapping("/delete/{uuid}")
    public void delete(final @PathVariable String uuid) {
        compositeService.delete(uuid);
    }
}
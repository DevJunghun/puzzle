package com.puzzle.iam.controller;

import com.puzzle.iam.controller.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user", produces = {MediaType.APPLICATION_JSON_VALUE})
@Slf4j
public class UserController {

    @PostMapping(value = "/kakao")
    public UserDto.Create.Response create(@RequestBody UserDto.Create.KakaoRequest request) {
        return new UserDto.Create.Response("uuid");
    }
    @PostMapping(value = "/google")
    public UserDto.Create.Response create(@RequestBody UserDto.Create.GoogleRequest request) {
        return new UserDto.Create.Response("uuid");
    }

    @PostMapping(value = "/naver")
    public UserDto.Create.Response create(@RequestBody UserDto.Create.NaverRequest request) {
        return new UserDto.Create.Response("uuid");
    }

}

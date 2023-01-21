package com.puzzle.api.feedback.controller;

import com.puzzle.api.feedback.controller.dto.FeedbackDto;
import com.puzzle.iam.controller.dto.AuthenticateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.puzzle.api.session.UserTokenHeader.HEADER_KEY;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/feedback", produces = {MediaType.APPLICATION_JSON_VALUE})
@Slf4j
public class FeedbackController {
    @PostMapping()
    public void authenticate(@RequestBody FeedbackDto.Request request, @RequestHeader(HEADER_KEY) String userToken) {
    }
}

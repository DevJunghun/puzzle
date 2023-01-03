package com.puzzle;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
@Slf4j
public class TestController {
    @GetMapping(value = "/")
    public void a() {
        log.info("asdfasdfasd");
        System.out.println("asdfadsasdf");
    }

}

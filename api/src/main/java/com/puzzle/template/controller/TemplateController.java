package com.puzzle.template.controller;

import com.puzzle.template.controller.dto.TemplateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.puzzle.api.session.UserTokenHeader.HEADER_KEY;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/template", produces = {MediaType.APPLICATION_JSON_VALUE})
@Slf4j
public class TemplateController {

    @GetMapping()
    public TemplateDto.GetList.Response getAllList(@RequestHeader(HEADER_KEY) String userToken) {
        return new TemplateDto.GetList.Response(List.of());
    }

    @GetMapping(value = "/{categoryUuid}")
    public TemplateDto.GetList.Response getTemplateList(final @PathVariable String categoryUuid, @RequestHeader(HEADER_KEY) String userToken) {
        return new TemplateDto.GetList.Response(List.of());
    }

    @PostMapping()
    public TemplateDto.Create.Response create(@RequestBody TemplateDto.Create.Request request, @RequestHeader(HEADER_KEY) String userToken) {
        return new TemplateDto.Create.Response("uuid");
    }

    @DeleteMapping()
    public void delete(@RequestHeader(HEADER_KEY) String userToken) {
    }

    @PatchMapping()
    public void favorite(@RequestBody TemplateDto.Favorite.Request request, @RequestHeader(HEADER_KEY) String userToken) {
    }

    @GetMapping(value = "/favorites")
    public TemplateDto.GetList.Response favoriteList() {
        return new TemplateDto.GetList.Response(List.of());
    }

    @GetMapping(value = "/made_by_user")
    public TemplateDto.GetList.Response madeByUserList() {
        return new TemplateDto.GetList.Response(List.of());
    }

}

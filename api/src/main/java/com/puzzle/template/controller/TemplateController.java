package com.puzzle.template.controller;

import com.puzzle.template.controller.dto.TemplateDto;
import jakarta.validation.Valid;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.puzzle.api.session.UserTokenHeader.HEADER_KEY;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/template", produces = {MediaType.APPLICATION_JSON_VALUE})
@Slf4j
public class TemplateController {

    @GetMapping(value = "/{categoryUuid}")
    public TemplateDto.GetList.Response getList(final @PathVariable String categoryUuid, @RequestHeader(HEADER_KEY) String userToken, @RequestParam String key, @RequestParam String value, @RequestParam String searchWord) {
        return new TemplateDto.GetList.Response(List.of());
    }

    @PostMapping()
    public TemplateDto.Create.Response create(@RequestBody @Valid TemplateDto.Create.Request request, @RequestHeader(HEADER_KEY) String userToken) {
        return new TemplateDto.Create.Response("uuid");
    }

    @DeleteMapping()
    public void delete(@RequestHeader(HEADER_KEY) String userToken, @RequestParam List<String> uuids) {
    }

    @PatchMapping()
    public void favorite(@RequestBody @Valid TemplateDto.Favorite.Request request, @RequestHeader(HEADER_KEY) String userToken) {
    }
}

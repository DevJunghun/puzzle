package com.puzzle.template.categories.controller;

import com.puzzle.template.categories.controller.dto.TemplateCategoryDto;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping(value = "/template/category", produces = {MediaType.APPLICATION_JSON_VALUE})
@Slf4j
public class TemplateCategoryController {
    @GetMapping()
    public TemplateCategoryDto.GetList.Response getList(@RequestHeader(HEADER_KEY) String userToken) {
        return new TemplateCategoryDto.GetList.Response(List.of());
    }

    @PostMapping()
    public TemplateCategoryDto.Create.Response create(final @RequestBody @Valid TemplateCategoryDto.Create.Request request, @RequestHeader(HEADER_KEY) String userToken) {
        return new TemplateCategoryDto.Create.Response("uuid");
    }

    @DeleteMapping("/{uuid}")
    public void delete(@RequestHeader(HEADER_KEY) String userToken,@PathVariable String uuid) {
    }
}

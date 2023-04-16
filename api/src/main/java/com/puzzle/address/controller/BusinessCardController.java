package com.puzzle.address.controller;

import com.puzzle.address.controller.dto.BusinessCardDto;
import com.puzzle.address.domain.BusinessCardCompositeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/business-card", produces = {MediaType.APPLICATION_JSON_VALUE})
@Slf4j
public class BusinessCardController {
    private final BusinessCardCompositeService compositeService;

    @GetMapping("/{uuid}")
    public BusinessCardDto.Get.Response get(final @PathVariable String uuid) {
        return compositeService.get(uuid);
    }

    @PatchMapping("/{uuid}")
    public void update(final @PathVariable String uuid, final @RequestBody BusinessCardDto.Update.Request request) {
        compositeService.update(uuid, request);
    }
}

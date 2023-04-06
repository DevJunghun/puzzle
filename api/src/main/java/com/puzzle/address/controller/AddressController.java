package com.puzzle.address.controller;

import com.puzzle.address.controller.dto.AddressDto;
import com.puzzle.address.domain.AddressCompositeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/address/{uuid}", produces = {MediaType.APPLICATION_JSON_VALUE})
@Slf4j
public class AddressController {
    private final AddressCompositeService compositeService;

    @GetMapping()
    public AddressDto.Get.Response get(final @PathVariable String uuid) {
        return compositeService.find(uuid);
    }

    @PatchMapping()
    public void update(final @PathVariable String uuid, final @RequestBody AddressDto.Update.Request request) {
        compositeService.update(uuid, request);
    }

    @DeleteMapping()
    public void delete(final @PathVariable String uuid) {
        compositeService.delete(uuid);
    }
}

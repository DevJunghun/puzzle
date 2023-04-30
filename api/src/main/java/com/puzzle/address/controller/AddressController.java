package com.puzzle.address.controller;

import com.puzzle.address.controller.dto.AddressDto;
import com.puzzle.address.domain.AddressCompositeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/address", produces = {MediaType.APPLICATION_JSON_VALUE})
@Slf4j
public class AddressController {
    private final AddressCompositeService compositeService;

    @GetMapping("/{uuid}")
    public AddressDto.Get.Response get(final @PathVariable String uuid) {
        return compositeService.find(uuid);
    }

    @PutMapping()
    public AddressDto.Create.Response create(final @Valid  @RequestBody AddressDto.Create.Request request) {
        return compositeService.create(request);
    }

    @PatchMapping("/{uuid}")
    public void update(final @PathVariable String uuid, final @RequestBody AddressDto.Update.Request request) {
        compositeService.update(uuid, request);
    }

    @DeleteMapping()
    public void delete(final @PathVariable String uuid) {
        compositeService.delete(uuid);
    }
}

package com.puzzle.address.controller;

import com.puzzle.address.domain.AddressGroupCompositeService;
import com.puzzle.address.controller.dto.AddressGroupDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/address-group/{userUuid}", produces = {MediaType.APPLICATION_JSON_VALUE})
@Slf4j
public class AddressGroupController {
    private final AddressGroupCompositeService compositeService;

    @GetMapping("/")
    public AddressGroupDto.GetAllGroups.Response getAll(final @PathVariable String userUuid) {
        return compositeService.findAll(userUuid);
    }

    @PutMapping("/")
    public AddressGroupDto.CreateParentGroup.Response createParent(final @PathVariable String userUuid, final @RequestBody AddressGroupDto.CreateParentGroup.Request request) {
        return compositeService.createParentGroup(userUuid, request);
    }

    @PutMapping("/{groupUuid}")
    public AddressGroupDto.CreateParentGroup.Response createChild(final @PathVariable String userUuid, final @PathVariable String groupUuid, final @RequestBody AddressGroupDto.CreateParentGroup.Request request) {
        return compositeService.createChildGroup(userUuid, groupUuid, request);
    }

    @PatchMapping("/{groupUuid}")
    public void update(final @PathVariable String userUuid, final @PathVariable String groupUuid, final @RequestBody AddressGroupDto.UpdateGroup.Request request) {
        compositeService.update(userUuid, groupUuid, request);
    }

    @DeleteMapping("/{groupUuid}")
    public void delete(final @PathVariable String userUuid, final @PathVariable String groupUuid) {
        compositeService.delete(userUuid, groupUuid);
    }
}

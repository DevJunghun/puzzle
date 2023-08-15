package com.puzzle.address.controller;

import com.puzzle.address.domain.AddressGroupCompositeService;
import com.puzzle.address.controller.dto.AddressGroupDto;
import com.puzzle.address.domain.AddressGroupTransactionService;
import com.puzzle.iam.domain.UserTokenTransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.puzzle.api.session.UserTokenHeader.HEADER_KEY;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/address-group", produces = {MediaType.APPLICATION_JSON_VALUE})
@Slf4j
public class AddressGroupController {
    private final UserTokenTransactionService userTokenTransactionService;
    private final AddressGroupCompositeService compositeService;
    private final AddressGroupTransactionService transactionService;

    @GetMapping("/")
    public AddressGroupDto.Response getAll(@RequestHeader(HEADER_KEY) String userToken) {
        final var user = userTokenTransactionService.findUser(userToken);

        return transactionService.findAll(user);
    }

    @PutMapping("/")
    public AddressGroupDto.Response createParent(final @RequestHeader(HEADER_KEY) String userToken, final @RequestBody AddressGroupDto.CreateParentGroup.Request request) {
        final var user = userTokenTransactionService.findUser(userToken);

        return transactionService.createParentGroup(user, request);
    }

    @PutMapping("/{groupUuid}")
    public AddressGroupDto.Response createChild(final @RequestHeader(HEADER_KEY) String userToken, final @PathVariable String groupUuid, final @RequestBody AddressGroupDto.CreateParentGroup.Request request) {
        final var user = userTokenTransactionService.findUser(userToken);

        return transactionService.createChildGroup(user, groupUuid, request);
    }

    @PatchMapping("/{groupUuid}")
    public AddressGroupDto.Response update(final @RequestHeader(HEADER_KEY) String userToken, final @PathVariable String groupUuid, final @RequestBody AddressGroupDto.UpdateGroup.Request request) {
        final var user = userTokenTransactionService.findUser(userToken);

        return transactionService.update(user, groupUuid, request);
    }

    @DeleteMapping("/{groupUuid}")
    public AddressGroupDto.Response delete(final @RequestHeader(HEADER_KEY) String userToken, final @PathVariable String groupUuid) {
        final var user = userTokenTransactionService.findUser(userToken);

        return transactionService.delete(user, groupUuid);
    }
}

package com.puzzle.address.domain;

import com.puzzle.address.controller.dto.BusinessCardDto;
import com.puzzle.api.util.BooleanDelete;
import com.puzzle.api.util.BooleanValidate;
import com.puzzle.api.util.Patch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BusinessCardCompositeService {
    private final BusinessCardService service;

    public BusinessCardDto.Get.Response get(final String uuid) {
        final var businessCard = service.find(uuid, BooleanDelete.FALSE, BooleanValidate.TRUE);

        return new BusinessCardDto.Get.Response(businessCard);
    }

    public void deleteByAddress(final String addressUuid) {
        final var businessCard = service.findByAddressUuid(addressUuid, BooleanDelete.FALSE, BooleanValidate.FALSE);

        service.delete(businessCard);
    }

    public BusinessCard find(final String uuid) {
        return service.find(uuid, BooleanDelete.FALSE, BooleanValidate.TRUE);
    }

    public void update(final String uuid, final BusinessCardDto.Update.Request request) {
        final var businessCard = service.find(uuid, BooleanDelete.FALSE, BooleanValidate.TRUE);

        final var patchBusinessCard = Patch.entityByRequest(businessCard, BusinessCard.class, request);

        service.update(patchBusinessCard);
    }
}

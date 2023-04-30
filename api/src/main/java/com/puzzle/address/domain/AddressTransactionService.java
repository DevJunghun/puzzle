package com.puzzle.address.domain;


import com.puzzle.address.controller.dto.AddressDto;
import com.puzzle.address.controller.dto.BusinessCardDto;
import com.puzzle.api.util.BooleanDelete;
import com.puzzle.api.util.BooleanValidate;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AddressTransactionService {
    private final AddressCompositeService compositeService;
    private final AddressService service;
    private final BusinessCardCompositeService businessCardCompositeService;
    private final AddressGroupCompositeService addressGroupCompositeService;

    @Transactional
    public AddressDto.Create.Response create(final AddressDto.Create.Request request) {
        addressGroupCompositeService.find(request.getGroupUuid());

        final var created = compositeService.create(request);

        businessCardCompositeService.create(created);

        return new AddressDto.Create.Response(created.getUuid());
    }

    @Transactional
    public AddressDto.Get.Response find(final String uuid) {
        final var address = service.find(uuid, BooleanDelete.FALSE, BooleanValidate.TRUE);

        final var response = new AddressDto.Get.Response();

        response.setName(address.getName());
        response.setEmail(address.getEmail());
        response.setUseCount(address.getUseCount());
        response.setPhoneNumber(address.getPhoneNumber());
        response.setRank(address.getRank());
        response.setDepartment(address.getDepartment());
        response.setCompanyName(address.getCompanyName());

        if (address.isHasBusinessCard()) {
            final var businessCard = businessCardCompositeService.findByAddressUuid(address.getUuid());

            response.setBusinessCard(new BusinessCardDto.BusinessCard(businessCard));
        }

        return response;
    }

}

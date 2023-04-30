package com.puzzle.address.domain;

import com.puzzle.address.controller.dto.AddressDto;
import com.puzzle.address.controller.dto.BusinessCardDto;
import com.puzzle.api.util.BooleanDelete;
import com.puzzle.api.util.BooleanValidate;
import com.puzzle.api.util.Patch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressCompositeService {
    private final AddressService service;
    private final BusinessCardCompositeService businessCardCompositeService;
    private final AddressGroupCompositeService addressGroupCompositeService;
    private final AddressTrigger addressTrigger;

    public AddressDto.Create.Response create(final AddressDto.Create.Request request) {
        addressGroupCompositeService.find(request.getGroupUuid());

        final var address = createAddress(request);

        final var created = service.create(address);

        return new AddressDto.Create.Response(created.getUuid());
    }

    public List<Address> findAll(final String groupUuid) {
        return service.findAll(groupUuid, BooleanDelete.TRUE);
    }

    public AddressDto.Get.Response find(final String uuid) {
        final var address = service.find(uuid, BooleanDelete.FALSE, BooleanValidate.TRUE);

        final var response = new AddressDto.Get.Response();

        response.setName(address.getName());
        response.setEmail(address.getEmail());
        response.setUseCount(address.getUseCount());
        response.setPhoneNumber(address.getPhoneNumber());

        if (address.isHasBusinessCard()) {
            final var businessCard = businessCardCompositeService.find(address.getUuid());

            response.setBusinessCard(new BusinessCardDto.BusinessCard(businessCard));
        }

        return response;
    }

    public void update(final String uuid, AddressDto.Update.Request request) {
        final var address = service.find(uuid, BooleanDelete.FALSE, BooleanValidate.TRUE);

        final var patchAddress = Patch.entityByRequest(address, Address.class, request);

        service.update(patchAddress);
    }

    public void delete(final String uuid) {
        final var address = service.find(uuid, BooleanDelete.FALSE, BooleanValidate.TRUE);

        addressTrigger.deleteBusinessCard(address);

        service.delete(address);
    }

    private Address createAddress(final AddressDto.Create.Request request) {
        final var address = new Address();

        address.setGroupUuid(request.getGroupUuid());
        address.setName(request.getName());
        address.setEmail(request.getEmail());
        address.setPhoneNumber(request.getPhoneNumber());
        address.setRank(request.getRank());
        address.setDepartment(request.getDepartment());
        address.setCompanyName(request.getCompanyName());

        return address;
    }

}

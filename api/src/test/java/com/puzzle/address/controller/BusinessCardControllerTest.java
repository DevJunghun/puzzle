package com.puzzle.address.controller;

import com.puzzle.RestClientFactory;
import com.puzzle.api.BaseTest;
import com.puzzle.utils.Const;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.TestFactory;
import org.springframework.context.annotation.Description;

import java.util.ArrayList;
import java.util.Collection;

class BusinessCardControllerTest extends BaseTest {
    private final static String CLASS_URL = "/business-card";

    @Description("business-card 에 대한 CRUD 를 테스트 한다.")
    @TestFactory
    Collection<DynamicNode> crud_test_for_business_card() {
        final var addressUuids = new ArrayList<String>();
        final var businessCardUuids = new ArrayList<String>();

        return group(
                group("address 를 만든다",
                    AddressControllerTest.create_address(addressUuids)
                ),

                single("business card uuid 를 받아온다.", () -> {
                    final var address = AddressControllerTest.getAddressInfo(addressUuids.get(0));

                    businessCardUuids.add(address.getJSONObject("businessCard").getString("uuid"));
                }),

                single("business card 가 자동생성 되었는지 확인한다.", () -> {
                    final var actual = RestClientFactory.get(CLASS_URL + "/" + businessCardUuids.get(0), null);

                    Assertions.assertAll(
                            () -> Assertions.assertEquals(Const.User.EMAIL, actual.getString("email")),
                            () -> Assertions.assertEquals("newAddress", actual.getString("name")),
                            () -> Assertions.assertEquals("leader", actual.getString("rank")),
                            () -> Assertions.assertEquals("01012345678", actual.getString("phoneNumber")),
                            () -> Assertions.assertEquals("puzzle", actual.getString("companyName"))
                    );
                }),

                single("business card 를 업데이트 한다.", () -> {
                    final var body = new JSONObject();

                    body.put("name", "newAddress2");
                    body.put("rank", "teamleader");
                    body.put("phoneNumber", "01012345679");
                    body.put("companyName", "puzzle");
                    body.put("companyUrl", "asda@google.com");
                    body.put("companyAddress", "Seoul");

                    RestClientFactory.patch(CLASS_URL + "/" + businessCardUuids.get(0), body);

                    final var actual = RestClientFactory.get(CLASS_URL + "/" + businessCardUuids.get(0), null);

                    Assertions.assertAll(
                            () -> Assertions.assertEquals(Const.User.EMAIL, actual.getString("email")),
                            () -> Assertions.assertEquals("newAddress2", actual.getString("name")),
                            () -> Assertions.assertEquals("teamleader", actual.getString("rank")),
                            () -> Assertions.assertEquals("01012345679", actual.getString("phoneNumber")),
                            () -> Assertions.assertEquals("puzzle", actual.getString("companyName")),
                            () -> Assertions.assertEquals("asda@google.com", actual.getString("companyUrl")),
                            () -> Assertions.assertEquals("Seoul", actual.getString("companyAddress"))
                    );
                })
        );
    }
}
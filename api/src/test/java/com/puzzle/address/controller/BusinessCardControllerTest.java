package com.puzzle.address.controller;

import com.puzzle.RestClientFactory;
import com.puzzle.api.BaseTest;
import com.puzzle.utils.Const;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.TestFactory;
import org.springframework.context.annotation.Description;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Supplier;

class BusinessCardControllerTest extends BaseTest {
    private final static String CLASS_URL = "/business-card";

    @Description("business-card 에 대한 CRUD 를 테스트 한다.")
    @TestFactory
    Collection<DynamicNode> crud_test_for_business_card() {
        final var addressUuids = new ArrayList<String>();

        return group(
                group("user create",  AddressControllerTest.create_address(addressUuids)),

                single("business card 가 자동생성 되었는지 확인한다.", () -> {
                    final var actual = RestClientFactory.get(CLASS_URL + "/" + addressUuids.get(0), null);

                    Assertions.assertAll(
                            () -> Assertions.assertEquals("email", Const.User.EMAIL),
                            () -> Assertions.assertEquals("name", actual.getString("newAddress")),
                            () -> Assertions.assertEquals("rank", actual.getString("leader")),
                            () -> Assertions.assertEquals("phoneNumber", actual.getString("01012345678")),
                            () -> Assertions.assertEquals("companyName", actual.getString("puzzle"))
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

                    RestClientFactory.patch(CLASS_URL + "/" + addressUuids.get(0), null);

                    final var actual = RestClientFactory.get(CLASS_URL + "/" + addressUuids.get(0), null);

                    Assertions.assertAll(
                            () -> Assertions.assertEquals("email", Const.User.EMAIL),
                            () -> Assertions.assertEquals("name", actual.getString("newAddress2")),
                            () -> Assertions.assertEquals("rank", actual.getString("teamleader")),
                            () -> Assertions.assertEquals("phoneNumber", actual.getString("01012345679")),
                            () -> Assertions.assertEquals("companyName", actual.getString("puzzle")),
                            () -> Assertions.assertEquals("companyUrl", actual.getString("asda@google.com")),
                            () -> Assertions.assertEquals("companyAddress", actual.getString("Seoul"))
                    );
                })
        );
    }
}
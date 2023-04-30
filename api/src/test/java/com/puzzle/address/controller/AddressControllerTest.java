package com.puzzle.address.controller;

import com.puzzle.RestClientFactory;
import com.puzzle.api.BaseTest;
import com.puzzle.iam.controller.UserControllerTest;
import com.puzzle.utils.Const;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.TestFactory;
import org.springframework.context.annotation.Description;

import java.util.ArrayList;
import java.util.Collection;

class AddressControllerTest extends BaseTest {
    private final static String CLASS_URL = "/address";

    @Description("address 에 대한 CRUD 를 테스트 한다.")
    @TestFactory
    Collection<DynamicNode> crud_test_for_address() {
        final var userUuids = new ArrayList<String>();
        final var addressGroupUuids = new ArrayList<String>();

        return group(
                UserControllerTest.create_user(Const.User.USER_NAME, Const.User.PASSWORD, Const.User.EMAIL, userUuids),

                single("addressgroup의 uuid 를 하나 가져온다.", () -> {
                    final var actual = RestClientFactory.get("/address-group" + "/" + userUuids.get(0) + "/", null);

                    final var addressGroupUuid = actual.getJSONArray("group").getJSONObject(0).getString("uuid");
                    addressGroupUuids.add(addressGroupUuid);
                }),

                single("address 를 생성한다.", () -> {
                    final var body = new JSONObject();

                    body.put("groupUuid", addressGroupUuids.get(0));
                    body.put("name", "newAddress");
                    body.put("email", Const.User.EMAIL);
                    body.put("phoneNumber", "01012345678");
                    body.put("rank", "leader");
                    body.put("department", "devlopment");
                    body.put("companyName", "puzzle");

                    final var actual = RestClientFactory.put(CLASS_URL, body);

                    Assertions.assertThat(true).isTrue();
                })
        );
    }



}
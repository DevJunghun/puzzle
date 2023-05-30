package com.puzzle.address.controller;

import com.puzzle.RestClientFactory;
import com.puzzle.api.BaseTest;
import com.puzzle.iam.controller.AuthenticateController;
import com.puzzle.iam.controller.AuthenticateControllerTest;
import com.puzzle.iam.controller.UserControllerTest;
import com.puzzle.utils.Const;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.TestFactory;
import org.springframework.context.annotation.Description;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class AddressControllerTest extends BaseTest {
    private final static String CLASS_URL = "/address";

    @Description("address 에 대한 CRUD 를 테스트 한다.")
    @TestFactory
    Collection<DynamicNode> crud_test_for_address() {
        final var userUuids = new ArrayList<String>();
        final var userTokens = new ArrayList<String>();
        final var addressGroupUuids = new ArrayList<String>();
        final var addressUuids = new ArrayList<String>();

        return group(
                AuthenticateControllerTest.login(userTokens),

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
                    body.put("department", "development");
                    body.put("companyName", "puzzle");

                    final var actual = RestClientFactory.put(CLASS_URL, body);

                    Assertions.assertThat(actual.getString("uuid")).isNotNull();
                    addressUuids.add(actual.getString("uuid"));
                }),

                single("address update", () -> {
                    final var body = new JSONObject();

                    body.put("name", "newAddress2");
                    body.put("email", Const.User.EMAIL);
                    body.put("phoneNumber", "01012345678");
                    body.put("rank", "teamleader");
                    body.put("department", "development");
                    body.put("companyName", "puzzleEmail");

                    RestClientFactory.patch(CLASS_URL + "/" + addressUuids.get(0), body);

                    final var actual = RestClientFactory.get(CLASS_URL + "/" + addressUuids.get(0), null);

                    org.junit.jupiter.api.Assertions.assertAll(
                            () -> org.junit.jupiter.api.Assertions.assertEquals(Const.User.EMAIL, actual.getString("email")),
                            () -> org.junit.jupiter.api.Assertions.assertEquals("newAddress2", actual.getString("name")),
                            () -> org.junit.jupiter.api.Assertions.assertEquals("teamleader", actual.getString("rank")),
                            () -> org.junit.jupiter.api.Assertions.assertEquals("01012345678", actual.getString("phoneNumber")),
                            () -> org.junit.jupiter.api.Assertions.assertEquals("puzzleEmail", actual.getString("companyName"))
                    );
                }),

                single("address delete", () -> {
                    RestClientFactory.delete(CLASS_URL + "/" + addressUuids.get(0));

                    final var addressGroups = RestClientFactory.get("/address-group" + "/" + userUuids.get(0) + "/", null);
                    final var addresses = addressGroups.getJSONArray("group").getJSONObject(0).getJSONArray("address");

                    Assertions.assertThat(addresses.length()).isEqualTo(0);
                })
        );
    }

    @Description("address 를 만든다.")
    @TestFactory
    public static Collection<DynamicNode> create_address(final List<String> addressUuids) {
        final var userUuids = new ArrayList<String>();
        return group(
                UserControllerTest.create_user(Const.User.USER_NAME, Const.User.PASSWORD, Const.User.EMAIL, userUuids),

                single("만든다.", () -> {

                    final var actual = RestClientFactory.get("/address-group" + "/" + userUuids.get(0) + "/", null);

                    final var addressGroupUuid = actual.getJSONArray("group").getJSONObject(0).getString("uuid");

                    final var body = new JSONObject();

                    body.put("groupUuid", addressGroupUuid);
                    body.put("name", "newAddress");
                    body.put("email", Const.User.EMAIL);
                    body.put("phoneNumber", "01012345678");
                    body.put("rank", "leader");
                    body.put("department", "development");
                    body.put("companyName", "puzzle");

                    final var addressCreated = RestClientFactory.put(CLASS_URL, body);

                    final var created = RestClientFactory.get(CLASS_URL + "/" + addressCreated.getString("uuid"), null);

                    org.junit.jupiter.api.Assertions.assertAll(
                            () -> org.junit.jupiter.api.Assertions.assertEquals(Const.User.EMAIL,created.getString("email")),
                            () -> org.junit.jupiter.api.Assertions.assertEquals("newAddress", created.getString("name")),
                            () -> org.junit.jupiter.api.Assertions.assertEquals("leader", created.getString("rank")),
                            () -> org.junit.jupiter.api.Assertions.assertEquals("01012345678", created.getString("phoneNumber")),
                            () -> org.junit.jupiter.api.Assertions.assertEquals("puzzle", created.getString("companyName"))
                    );

                    addressUuids.add(addressCreated.getString("uuid"));
                })
        );
    }

    public static JSONObject getAddressInfo(final String uuid) {
        return RestClientFactory.get(CLASS_URL + "/" + uuid, null);
    }
}

package com.puzzle.address.controller;

import com.puzzle.RestClientFactory;
import com.puzzle.api.BaseTest;
import com.puzzle.iam.controller.AuthenticateControllerTest;
import com.puzzle.utils.Const;
import org.json.JSONObject;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.TestFactory;
import org.springframework.context.annotation.Description;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertAll;


class AddressGroupControllerTest extends BaseTest {
    private final static String CLASS_URL = "/address-group";
    private final static String GET_ALL = "/";
    private final static String CREATE_PARENT = "/";

    @Description("유저를 생성하면 default address group 이 생성되어야 한다.")
    @TestFactory
    Collection<DynamicNode> create_user_create_default_address_group() {
        final var userTokens = new ArrayList<String>();

        return group(
                AuthenticateControllerTest.login(userTokens),

                single("default address group 이 생성되었는지 확인한다.", () -> {
                    final var actual = RestClientFactory.get(CLASS_URL + GET_ALL, null, userTokens.get(0));

                    org.assertj.core.api.Assertions
                            .assertThat(actual
                                    .getJSONArray("group")
                                    .getJSONObject(0)
                                    .get("name"))
                            .isEqualTo(Const.AddressGroup.DEFAULT_NAME);
                })
        );
    }

    @Description("새로운 address group 을 생성하고 createChild, update, delete 를 테스트한다.")
    @TestFactory
    Collection<DynamicNode> crud_test_for_parent_group() {
        final var userTokens = new ArrayList<String>();
        final var groupUuids = new ArrayList<String>();

        return group(
                AuthenticateControllerTest.login(userTokens),

                single("parent address group 을 생성한다.", () -> {
                    final var body = new JSONObject();

                    body.put("name", "Parent");

                    final var actual = RestClientFactory.put(CLASS_URL + CREATE_PARENT, body, userTokens.get(0));
                    org.assertj.core.api.Assertions.assertThat(actual.get("uuid")).isNotNull();

                    final var parentGroups = RestClientFactory.get(CLASS_URL + GET_ALL, null, userTokens.get(0)).getJSONArray("group");
                    org.assertj.core.api.Assertions.assertThat(parentGroups.length()).isEqualTo(2);

                    for (int i = 0; i < parentGroups.length(); i++) {
                        final var group = parentGroups.getJSONObject(i);

                        groupUuids.add(group.getString("uuid"));
                    }
                }),

                group("새로 생성한 parent address group의 child group를 생성된다.",
                        single("생성 테스트", () -> {
                            final var body = new JSONObject();

                            body.put("name", "Child");

                            final var actual = RestClientFactory.put(CLASS_URL + "/" + groupUuids.get(1), body, userTokens.get(0));

                            org.assertj.core.api.Assertions.assertThat(actual.get("uuid")).isNotNull();

                            groupUuids.add(actual.getString("uuid"));
                        }),

                        single("그 때 get all test", () -> {
                            final var actual = RestClientFactory.get(CLASS_URL + GET_ALL, null, userTokens.get(0));

                            final var newGroup = actual.getJSONArray("group").getJSONObject(1);
                            final var newChildGroup = newGroup.getJSONArray("innerGroups");
                            groupUuids.add(newChildGroup.getJSONObject(0).getString("uuid"));

                            assertAll(
                                    () -> org.assertj.core.api.Assertions.assertThat(newGroup.getString("name")).isEqualTo("Parent"),
                                    () -> org.assertj.core.api.Assertions.assertThat(newChildGroup).isNotNull(),
                                    () -> org.assertj.core.api.Assertions.assertThat(newChildGroup.length()).isEqualTo(1),
                                    () -> org.assertj.core.api.Assertions.assertThat(newChildGroup.getJSONObject(0).getString("name")).isEqualTo("Child"),
                                    () -> org.assertj.core.api.Assertions.assertThat(newChildGroup.getJSONObject(0).getInt("size")).isEqualTo(0)
                            );
                        })
                ),

                group("새로 생성한 parent address group의 child group의 childe group를 생성된다.",
                        single("생성 테스트", () -> {
                            final var body = new JSONObject();

                            body.put("name", "ChildChild");

                            final var actual = RestClientFactory.put(CLASS_URL + "/" + groupUuids.get(2), body, userTokens.get(0));

                            org.assertj.core.api.Assertions.assertThat(actual.get("uuid")).isNotNull();

                            groupUuids.add(actual.getString("uuid"));
                        }),

                        single("그 때 get all test", () -> {
                            final var actual = RestClientFactory.get(CLASS_URL + GET_ALL, null, userTokens.get(0));

                            final var newGroup = actual.getJSONArray("group").getJSONObject(1);
                            final var newChildGroup = newGroup.getJSONArray("innerGroups").getJSONObject(0).getJSONArray("innerGroups");

                            assertAll(
                                    () -> org.assertj.core.api.Assertions.assertThat(newGroup.getString("name")).isEqualTo("Parent"),
                                    () -> org.assertj.core.api.Assertions.assertThat(newChildGroup).isNotNull(),
                                    () -> org.assertj.core.api.Assertions.assertThat(newChildGroup.length()).isEqualTo(1),
                                    () -> org.assertj.core.api.Assertions.assertThat(newChildGroup.getJSONObject(0).getString("name")).isEqualTo("ChildChild"),
                                    () -> org.assertj.core.api.Assertions.assertThat(newChildGroup.getJSONObject(0).getInt("size")).isEqualTo(0)
                            );
                        })
                ),

                single("child group update", () -> {
                    final var body = new JSONObject();

                    body.put("name", "Child2");

                    RestClientFactory.patch(CLASS_URL + "/" + groupUuids.get(3), body, userTokens.get(0));

                    final var actual = RestClientFactory.get(CLASS_URL + GET_ALL, null, userTokens.get(0));

                    final var newGroup = actual.getJSONArray("group").getJSONObject(1);
                    final var newChildGroup = newGroup.getJSONArray("innerGroups");

                    assertAll(
                            () -> org.assertj.core.api.Assertions.assertThat(newGroup.getString("name")).isEqualTo("Parent"),
                            () -> org.assertj.core.api.Assertions.assertThat(newChildGroup).isNotNull(),
                            () -> org.assertj.core.api.Assertions.assertThat(newChildGroup.length()).isEqualTo(1),
                            () -> org.assertj.core.api.Assertions.assertThat(newChildGroup.getString(0)).isEqualTo("Child2")
                    );
                }),

                single("child group delete", () -> {

                    final var actual = RestClientFactory.delete(CLASS_URL + "/" + groupUuids.get(3), userTokens.get(0));

                    final var groups = RestClientFactory.get(CLASS_URL + GET_ALL, null, userTokens.get(0));

                    final var newGroup = groups.getJSONArray("group").getJSONObject(1);

                    assertAll(
                            () -> org.assertj.core.api.Assertions.assertThat(newGroup.getString("name")).isEqualTo("Parent"),
                            () -> org.assertj.core.api.Assertions.assertThat(String.valueOf(newGroup.get("innerGroups"))).isEqualTo("null")
                    );
                })

        );
    }

}
package com.puzzle.iam.controller;

import com.puzzle.RestClientFactory;
import com.puzzle.api.BaseTest;
import jdk.jfr.Description;
import org.json.JSONObject;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.TestFactory;

import java.util.Collection;

class UserControllerTest extends BaseTest {
    private final static String CLASS_URL = "/user";

    @Description("유저 로그인 테스트")
    @TestFactory
    Collection<DynamicNode> create_user() {
        final var createUserUri = CLASS_URL + "/sign-in";
        return group(

                single("올바르게 유저를 생성한다.", () -> {

                    final var createRequest = new JSONObject();

                    createRequest.put("id", "dgoh1620");
                    createRequest.put("pwd", "password12");
                    createRequest.put("username", "roganOh");
                    createRequest.put("email", "fdscbjdcnhd@gmail.com");

                    final var actual = RestClientFactory.put(createUserUri, createRequest);

                    org.assertj.core.api.Assertions.assertThat(actual.get("uuid")).isNotNull().isNotEqualTo("");
                }),

                single("이미 존재하는 유저 아이디로 유저를 생성하면 에러.", () -> {

                    final var createRequest = new JSONObject();

                    createRequest.put("id", "dgoh1620");
                    createRequest.put("pwd", "password12");
                    createRequest.put("username", "roganOh");
                    createRequest.put("email", "fdscbjdcnhd2@gmail.com");

                    final var actual = RestClientFactory.put(createUserUri, createRequest);

                    org.assertj.core.api.Assertions.assertThat(actual.get("uuid")).isNotNull().isNotEqualTo("");
                }),

                single("이미 존재하는 유저 이메일로 유저를 생성하면 에러.", () -> {

                    final var createRequest = new JSONObject();

                    createRequest.put("id", "dgoh1621");
                    createRequest.put("pwd", "password12");
                    createRequest.put("username", "roganOh");
                    createRequest.put("email", "fdscbjdcnhd@gmail.com@gmail.com");

                    final var actual = RestClientFactory.put(createUserUri, createRequest);
                })
        );
    }
}

package com.puzzle.iam.controller;

import com.puzzle.RestClientFactory;
import com.puzzle.api.BaseTest;
import com.puzzle.iam.domain.exceptions.AlreadyExistUserException;
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

                    createRequest.put("username", "roganOh");
                    createRequest.put("pwd", "password12");
                    createRequest.put("email", "fdscbjdcnhd@gmail.com");

                    final var actual = RestClientFactory.put(createUserUri, createRequest);

                    org.assertj.core.api.Assertions.assertThat(actual.get("uuid")).isNotNull().isNotEqualTo("");
                }),

                single("이미 존재하는 유저 아이디로 유저를 생성하면 에러.", () -> {

                    final var createRequest = new JSONObject();

                    createRequest.put("username", "roganOh");
                    createRequest.put("pwd", "password12");
                    createRequest.put("email", "fdscbjdcnhd2@gmail.com");

                    RestClientFactory.putAssertFail(createUserUri, createRequest, AlreadyExistUserException.class);
                }),

                single("이미 존재하는 메인 이메일로 유저를 생성하면 에러.", () -> {

                    final var createRequest = new JSONObject();

                    createRequest.put("username", "roganOh2");
                    createRequest.put("pwd", "password12");
                    createRequest.put("email", "fdscbjdcnhd@gmail.com");

                    RestClientFactory.putAssertFail(createUserUri, createRequest, AlreadyExistUserException.class);
                })
        );
    }
}

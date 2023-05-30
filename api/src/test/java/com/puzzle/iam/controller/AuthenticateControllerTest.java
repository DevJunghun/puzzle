package com.puzzle.iam.controller;

import com.puzzle.RestClientFactory;
import com.puzzle.api.BaseTest;
import com.puzzle.iam.domain.exceptions.InvalidCredentialException;
import com.puzzle.iam.domain.exceptions.UserLockedException;
import com.puzzle.iam.domain.exceptions.UserNotExistException;
import com.puzzle.utils.Const;
import jdk.jfr.Description;
import org.json.JSONObject;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.TestFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AuthenticateControllerTest extends BaseTest {
    private final static String CLASS_URL = "/authenticate";
    private final static String LOGIN_URL = CLASS_URL + "/auth";

    @Description("유저 로그인 테스트")
    @TestFactory
    Collection<DynamicNode> login() {
        final var userUuids = new ArrayList<String>();
        final var userTokens = new ArrayList<String>();

        return group(
                UserControllerTest.create_user(Const.User.USER_NAME, Const.User.PASSWORD, Const.User.EMAIL, userUuids),

                single("정상적으로 로그인한다.", () -> {
                    final var postRequest = new JSONObject();

                    postRequest.put("username", Const.User.USER_NAME);
                    postRequest.put("password", Const.User.PASSWORD);

                    final var actual = RestClientFactory.post(LOGIN_URL, postRequest);

                    org.assertj.core.api.Assertions.assertThat(actual.get("userToken")).isNotNull().isNotEqualTo("");

                    userTokens.add(actual.getString("userToken"));
                }),

                single("없는 아이디로 로그인한다.", () -> {
                    final var postRequest = new JSONObject();

                    postRequest.put("username", "noExistUsername");
                    postRequest.put("password", Const.User.PASSWORD);

                    RestClientFactory.postAssertFail(LOGIN_URL, postRequest, UserNotExistException.class);
                }),

                single("틀린 패스워드로 로그인한다.", () -> {
                    final var postRequest = new JSONObject();

                    postRequest.put("username", Const.User.USER_NAME);
                    postRequest.put("password", "wrongpassword");

                    RestClientFactory.postAssertFail(LOGIN_URL, postRequest, InvalidCredentialException.class);
                }),

                single("정상적으로 로그인한다.", () -> {
                    final var postRequest = new JSONObject();

                    postRequest.put("username", Const.User.USER_NAME);
                    postRequest.put("password", Const.User.PASSWORD);

                    final var actual = RestClientFactory.post(LOGIN_URL, postRequest);

                    org.assertj.core.api.Assertions.assertThat(actual.get("userToken")).isNotNull().isNotEqualTo("");

                    userTokens.add(actual.getString("userToken"));
                }),

                single("틀린 패스워드로 로그인한다. x4", () -> {
                    for (int i = 0; i < 4; i++) {

                        final var postRequest = new JSONObject();

                        postRequest.put("username", Const.User.USER_NAME);
                        postRequest.put("password", "wrongpassword");

                        RestClientFactory.postAssertFail(LOGIN_URL, postRequest, InvalidCredentialException.class);
                    }
                }),

                single("한번 틀리고 성공 + 네번 틀리면 유저가 락되지 않는것을 확인한다.", () -> {
                    final var postRequest = new JSONObject();

                    postRequest.put("username", Const.User.USER_NAME);
                    postRequest.put("password", Const.User.PASSWORD);

                    final var actual = RestClientFactory.post(LOGIN_URL, postRequest);

                    org.assertj.core.api.Assertions.assertThat(actual.get("userToken")).isNotNull().isNotEqualTo("");

                    userTokens.add(actual.getString("userToken"));
                }),

                single("틀린 패스워드로 로그인한다. x5", () -> {
                    for (int i = 0; i < 5; i++) {

                        final var postRequest = new JSONObject();

                        postRequest.put("username", Const.User.USER_NAME);
                        postRequest.put("password", "wrongpassword");

                        RestClientFactory.postAssertFail(LOGIN_URL, postRequest, InvalidCredentialException.class);
                    }
                }),

                single("다섯번 연속으로 틀리면 유저가 락되는 것을 확인한다.", () -> {
                    final var postRequest = new JSONObject();

                    postRequest.put("username", Const.User.USER_NAME);
                    postRequest.put("password", Const.User.PASSWORD);

                    RestClientFactory.postAssertFail(LOGIN_URL, postRequest, UserLockedException.class);
                })
        );
    }

    static public DynamicNode login(final List<String> userTokens) {
        final var userUuids = new ArrayList<String>();

        return group("로그인을 한다.",
                UserControllerTest.create_user(Const.User.USER_NAME, Const.User.PASSWORD, Const.User.EMAIL, userUuids),

                single("정상적으로 로그인한다.", () -> {
                    final var postRequest = new JSONObject();

                    postRequest.put("username", Const.User.USER_NAME);
                    postRequest.put("password", Const.User.PASSWORD);

                    final var actual = RestClientFactory.post(LOGIN_URL, postRequest);

                    org.assertj.core.api.Assertions.assertThat(actual.get("userToken")).isNotNull().isNotEqualTo("");

                    userTokens.add(actual.getString("userToken"));
                })
        );
    }
}


package com.puzzle.iam.controller;

import com.puzzle.RestClientFactory;
import com.puzzle.api.BaseTest;
import com.puzzle.iam.domain.exceptions.AlreadyExistUserException;
import com.puzzle.iam.domain.exceptions.NotValidPasswordException;
import jdk.jfr.Description;
import org.json.JSONObject;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.TestFactory;

import java.util.ArrayList;
import java.util.Collection;

public class UserControllerTest extends BaseTest {
    private final static String CLASS_URL = "/user";
    private final static String CREATE_USER_URL = CLASS_URL + "/sign-up";

    @Description("유저 로그인 테스트")
    @TestFactory
    Collection<DynamicNode> create_user() {
        final var usernames = new ArrayList<String>();
        final var pwds = new ArrayList<String>();
        final var emails = new ArrayList<String>();
        final var userUuids = new ArrayList<String>();

        usernames.add("roganOh");
        pwds.add("password12");
        emails.add("fdscbjdcnhd@gmail.com");

        return group(

                create_user(usernames.get(0), pwds.get(0), emails.get(0), userUuids),

                single("이미 존재하는 유저 아이디로 유저를 생성하면 에러.", () -> {

                    final var createRequest = new JSONObject();

                    createRequest.put("username", usernames.get(0));
                    createRequest.put("pwd", "password12");
                    createRequest.put("email", "fdscbjdcnhd2@gmail.com");

                    RestClientFactory.putAssertFail(CREATE_USER_URL, createRequest, AlreadyExistUserException.class);
                }),

                single("이미 존재하는 메인 이메일로 유저를 생성하면 에러.", () -> {

                    final var createRequest = new JSONObject();

                    createRequest.put("username", "roganOh2");
                    createRequest.put("pwd", pwds.get(0));
                    createRequest.put("email", "fdscbjdcnhd@gmail.com");

                    RestClientFactory.putAssertFail(CREATE_USER_URL, createRequest, AlreadyExistUserException.class);
                }),

                single("패스워드에 숫자 없으면 에러", () -> {

                    final var createRequest = new JSONObject();

                    createRequest.put("username", "roganOh3");
                    createRequest.put("pwd", "password");
                    createRequest.put("email", "fdscbjdcnhd2@gmail.com");

                    RestClientFactory.putAssertFail(CREATE_USER_URL, createRequest, NotValidPasswordException.class);
                }),

                single("패스워드에 영어 없으면 에러", () -> {

                    final var createRequest = new JSONObject();

                    createRequest.put("username", "roganOh3");
                    createRequest.put("pwd", "123412412");
                    createRequest.put("email", "fdscbjdcnhd2@gmail.com");

                    RestClientFactory.putAssertFail(CREATE_USER_URL, createRequest, NotValidPasswordException.class);
                })
        );
    }


    static public DynamicNode create_user(final String username, final String pwd, final String email, final ArrayList<String> userUuids) {
        return single("올바르게 유저를 생성한다.", () -> {

            final var createRequest = new JSONObject();

            createRequest.put("username", username);
            createRequest.put("pwd", pwd);
            createRequest.put("email", email);

            final var actual = RestClientFactory.put(CREATE_USER_URL, createRequest);

            org.assertj.core.api.Assertions.assertThat(actual.get("uuid")).isNotNull().isNotEqualTo("");

            userUuids.add(actual.getString("uuid"));
        });
    }
}

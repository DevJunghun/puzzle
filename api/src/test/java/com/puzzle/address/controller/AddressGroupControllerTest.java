package com.puzzle.address.controller;

import com.puzzle.RestClientFactory;
import com.puzzle.api.BaseTest;
import com.puzzle.iam.controller.UserControllerTest;
import com.puzzle.utils.Const;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.TestFactory;
import org.springframework.context.annotation.Description;

import java.util.ArrayList;
import java.util.Collection;


class AddressGroupControllerTest extends BaseTest {
    private final static String CLASS_URL = "/address-group";
    private final static String GET_ALL = "/";

    @Description("유저를 생성하면 default address group 이 생성되어야 한다.")
    @TestFactory
    Collection<DynamicNode> create_user_create_default_address_group() {
        final var userUuids = new ArrayList<String>();
        return group(
                UserControllerTest.create_user(Const.User.USER_NAME, Const.User.PASSWORD, Const.User.EMAIL, userUuids),

                single("default address group 이 생성되었는지 확인한다.", () -> {
                    final var addressGroupResponse = RestClientFactory.get(CLASS_URL + "/" + userUuids.get(0) + GET_ALL, null);

                    System.out.println("asdfadsfd");
                })
        );
    }

}
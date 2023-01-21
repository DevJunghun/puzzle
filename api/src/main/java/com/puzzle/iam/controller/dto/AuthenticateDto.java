package com.puzzle.iam.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class AuthenticateDto {
    @Getter
    public class Kakao {
        private String username;
        private String password;
    }

    @Getter
    public class Google {
        private String username;
        private String password;
    }

    @Getter
    public class Naver {
        private String username;
        private String password;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private String username;
        private String token;
    }
}

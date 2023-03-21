package com.puzzle.iam.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class AuthenticateDto {
    public class LogIn {
        @Getter
        public static class Request {
            private String username;
            private String password;
        }

        @AllArgsConstructor
        public static class Response {
            private String userToken;

        }
    }
}
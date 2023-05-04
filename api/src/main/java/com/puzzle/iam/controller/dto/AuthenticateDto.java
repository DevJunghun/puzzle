package com.puzzle.iam.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class AuthenticateDto {
    public class LogIn {
        @Getter
        @Schema(name = "AuthenticateDto.LogIn.Request")
        public static class Request {
            private String username;
            private String password;
        }

        @AllArgsConstructor
        @Getter
        @Schema(name = "AuthenticateDto.LogIn.Response")
        public static class Response {
            private String userToken;

        }
    }
}
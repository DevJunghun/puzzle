package com.puzzle.iam.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class UserDto {
    public static class Create {
        @Getter
        public static class KakaoRequest{
            private String username;
            private String password;
        }

        @Getter
        public static class GoogleRequest {
            private String username;
            private String password;
        }

        @Getter
        public static class NaverRequest {
            private String username;
            private String password;
        }

        @Getter
        @AllArgsConstructor
        public static class Response {
            private String uuid;
        }
    }
}

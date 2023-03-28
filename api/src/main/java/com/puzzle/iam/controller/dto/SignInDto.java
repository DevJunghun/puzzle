package com.puzzle.iam.controller.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class SignInDto {
    public static class Create {
        @Getter
        public static class Request {
            @NotEmpty
            @Size(max = 20)
            private String id;
            @NotEmpty
            @Size(min = 6, max = 20)
            private String pwd;
            @NotEmpty
            @Size(max = 20)
            private String username;

            @NotEmpty
            @Size(max = 50)
            private String email;
        }

        @AllArgsConstructor
        @Getter
        public static class Response {
            private String uuid;
        }
    }
}

package com.puzzle.address.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

public class AddressDto {

    public static class Create {
        @Getter
        public static class Request {
            @NotEmpty
            private String groupUuid;
            @NotEmpty
            @Length(max = 20)
            private String name;
            @NotEmpty
            @Email
            private String email;
            @Length(max = 20)
            private String phoneNumber;
            @Length(max = 20)
            private String rank;
            @Length(max = 20)
            private String department;
            @Length(max = 20)
            private String companyName;

        }

        @AllArgsConstructor
        @Getter
        public static class Response {
            private String uuid;
        }
    }

    public static class Get {
        @Setter
        @Getter
        public static class Response {
            private String name;
            private String email;
            private String phoneNumber;
            private String rank;
            private String department;
            private String companyName;
            private long useCount;
            private BusinessCardDto.BusinessCard businessCard;
        }
    }

    public static class Update {
        @Getter
        public static class Request {
            @Length(max = 20)
            private String name;
            @Email
            private String email;
            @Length(max = 20)
            private String phoneNumber;
            @Length(max = 20)
            private String rank;
            @Length(max = 20)
            private String department;
            @Length(max = 20)
            private String companyName;

            public Request() {
            }
        }
    }
}

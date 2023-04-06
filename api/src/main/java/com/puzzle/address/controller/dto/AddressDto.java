package com.puzzle.address.controller.dto;

import lombok.Getter;
import lombok.Setter;

public class AddressDto {
    public static class Get{
        @Setter
        @Getter
        public static class Response {
            private String name;
            private String email;
            private String phoneNumber;
            private long useCount;
            private String businessCardUuid;
            private String businessCardContent;
        }
    }

    public static class Update{
        @Getter
        public static class Request {
            private String name;
            private String email;
            private String phoneNumber;
        }
    }
}

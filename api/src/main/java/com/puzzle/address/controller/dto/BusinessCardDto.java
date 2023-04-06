package com.puzzle.address.controller.dto;

import com.puzzle.address.domain.BusinessCard;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class BusinessCardDto {
    public static class Get {
        @Getter
        @Setter
        @NoArgsConstructor
        public static class Response{
            private String name;
            private String rank;
            private String companyName;
            private String companyUrl;
            private String companyAddress;

            public Response(BusinessCard businessCard) {
                final var response = new Response();

                response.setName(businessCard.getName());
                response.setRank(businessCard.getRank());
                response.setCompanyName(businessCard.getCompanyName());
                response.setCompanyUrl(businessCard.getCompanyUrl());
                response.setCompanyAddress(businessCard.getCompanyAddress());
            }
        }
    }

    public static class Update{
        @Getter
        public static class Request{
            private String name;
            private String rank;
            private String companyName;
            private String companyUrl;
            private String companyAddress;
        }
    }
}

package com.puzzle.address.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class AddressGroupDto {
    public static class GetAllGroups {
        @AllArgsConstructor
        @Getter
        @Schema(name = "AddressGroupDto.GetAllGroups.Response")
        public static class Response {
            private List<Group> group;
        }
    }

    public static class CreateParentGroup{
        @Getter
        @Schema(name = "AddressGroupDto.CreateParentGroup.Request")
        public static class Request{
            @NotEmpty
            @Max(20)
            private String name;
        }

        @AllArgsConstructor
        @Getter
        @Schema(name = "AddressGroupDto.CreateParentGroup.Response")
        public static class Response{
            private String uuid;
        }
    }

    public static class UpdateGroup {
        @Getter
        @Schema(name = "AddressGroupDto.UpdateGroup.Request")
        public static class Request {
            @NotEmpty
            @Max(20)
            private String name;
        }
    }

    @AllArgsConstructor
    @Setter
    @Getter
    @Schema(name = "AddressGroupDto.Group")
    public static class Group{
        private String name;
        private String uuid;
        private List<String> innerGroupNames;
        private List<Address> address;
    }

    @AllArgsConstructor
    @Getter
    @Schema(name = "AddressGroupDto.Address")
    public static class Address{
        private String name;
        private String uuid;
    }
}

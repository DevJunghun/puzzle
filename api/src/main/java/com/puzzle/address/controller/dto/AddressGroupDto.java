package com.puzzle.address.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class AddressGroupDto {
    @AllArgsConstructor
    @Getter
    @Schema(name = "AddressGroupDto.AddressGroup.Response")
    public static class Response {
        private List<Group> groups;
    }

    public static class GetAllGroups {
        @AllArgsConstructor
        @Getter
        @Schema(name = "AddressGroupDto.GetAllGroups.Response")
        public static class Response {
            private List<Group> group;
        }
    }

    public static class CreateParentGroup {
        @Getter
        @Schema(name = "AddressGroupDto.CreateParentGroup.Request")
        public static class Request {
            @NotEmpty
            @Max(20)
            private String name;
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
    public static class Group {
        private String name;
        private String uuid;
        private List<Group> innerGroups;
        private List<Address> address;
        private int size;

        public void addAllAddressSize(final List<Group> groups) {
            groups.forEach(group -> {
                this.size += group.getSize();
            });
        }
    }

    @AllArgsConstructor
    @Getter
    @Schema(name = "AddressGroupDto.Address")
    public static class Address {
        private String name;
        private String uuid;
    }
}

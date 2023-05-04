package com.puzzle.template.categories.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

public class TemplateCategoryDto {

    public static class GetList {
        @Getter
        @AllArgsConstructor
        @Schema(name = "TemplateCategoryDto.GetList.Response")
        public static class Response {
            private List<Category> categories;

        }
    }

    public static class Create {
        @Getter
        @Schema(name = "TemplateCategoryDto.Create.Request")
        public static class Request {
            private String name;
        }

        @Getter
        @AllArgsConstructor
        @Schema(name = "TemplateCategoryDto.Create.Response")
        public static class Response {
            private String uuid;
        }
    }

    public static class Delete {
        @Getter
        public static class Request {
            private String uuid;
        }
    }

    @Getter
    private static class Category {
        private String uuid;
        private String name;
    }
}

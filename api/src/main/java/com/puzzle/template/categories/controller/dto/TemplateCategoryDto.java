package com.puzzle.template.categories.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

public class TemplateCategoryDto {

    public static class GetList {
        @Getter
        @AllArgsConstructor
        public static class Response {
            private List<Category> categories;

        }
    }

    public static class Create {
        @Getter
        public static class Request {
            private String name;
        }

        @Getter
        @AllArgsConstructor
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

    private static class Category {
        private String uuid;
        private String name;
    }
}

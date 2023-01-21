package com.puzzle.template.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
public class TemplateDto {
    public static class Create {
        @Getter
        public static class Request {
            private String name;
            private String categoryUuid;
            private String content;
        }

        @Getter
        @AllArgsConstructor
        public static class Response {
            private String uuid;
        }

    }

    public static class GetList {

        @Getter
        @AllArgsConstructor
        public static class Response {
            private List<Template> templates;
        }
    }
    public static class Delete {
        @Getter
        public static class Request{

        }

    }

    public static class Favorite {
        public static class Request{
            private String TemplateUuid;
        }
    }

    @Getter
    @AllArgsConstructor
    private static class Template {
        private String uuid;
        private String name;
        private boolean favorite;
        private boolean madeByUser;
    }
}

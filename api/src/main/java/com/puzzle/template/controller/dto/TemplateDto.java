package com.puzzle.template.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Getter
public class TemplateDto {
    @Getter
    public static class Create {
        @Getter
        @Schema(name = "TemplateDto.Create.Request")
        public static class Request {
            @NotEmpty
            @Length(max = 20)
            private String name;
            @NotEmpty
            private String categoryUuid;
            @NotEmpty
            private String content;
        }

        @Getter
        @AllArgsConstructor
        @Schema(name = "TemplateDto.Create.Response")
        public static class Response {
            private String uuid;
        }

    }

    public static class GetList {

        @Getter
        @AllArgsConstructor
        @Schema(name = "TemplateDto.GetList.Response")
        public static class Response {
            private List<Template> templates;
        }
    }
    public static class Delete {
        @Getter
        public static class Request{

        }

    }

    @Getter
    public static class Favorite {
        @Getter
        @Schema(name = "TemplateDto.Favorite.Request")
        public static class Request{
            @NotEmpty
            private String TemplateUuid;
        }
    }

    @Getter
    @AllArgsConstructor
    private static class Template {
        private String uuid;
        private String name;
        private boolean favorite;
    }
}

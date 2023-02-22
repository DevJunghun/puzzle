package com.puzzle;

import com.puzzle.api.config.DataSourcePropertyLoader;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class PuzzleApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(PuzzleApplication.class).listeners(new DataSourcePropertyLoader()).run(args);
    }

}

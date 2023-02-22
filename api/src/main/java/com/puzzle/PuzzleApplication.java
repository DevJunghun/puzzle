package com.puzzle;

import com.puzzle.api.config.DataSourcePropertyLoader;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.web.WebApplicationInitializer;

@Slf4j
@SpringBootApplication
public class PuzzleApplication extends SpringBootServletInitializer implements WebApplicationInitializer, ApplicationListener<ApplicationReadyEvent> {

    private static final Class<PuzzleApplication> APPLICATION_SOURCE = PuzzleApplication.class;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        log.info("Puzzle application successfully started.");
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(APPLICATION_SOURCE).listeners(new DataSourcePropertyLoader()).run(args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        this.setRegisterErrorPageFilter(false);
        return application.sources(APPLICATION_SOURCE).listeners(new DataSourcePropertyLoader());
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
    }
}

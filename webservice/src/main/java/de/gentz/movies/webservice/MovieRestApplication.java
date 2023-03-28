package de.gentz.movies.webservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"de.gentz.movies.repository"})
@EntityScan("de.gentz.movies.entity")
public class MovieRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieRestApplication.class, args);
    }
}

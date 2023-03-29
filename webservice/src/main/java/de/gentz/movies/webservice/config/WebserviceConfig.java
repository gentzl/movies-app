package de.gentz.movies.webservice.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"de.gentz.movies.repository"})
@EntityScan("de.gentz.movies.entity")
public class WebserviceConfig {
}

package com.works.leagueservice.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author mali.sahin
 * @since 2019-06-26.
 */
@Configuration
@EntityScan(basePackages = "com.works.leagueservice.domain")
@EnableJpaRepositories("com.works.leagueservice.repository")
@ComponentScan(basePackages = {"com.works.leagueservice.mappingservice",
        "com.works.leagueservice.service",
        "com.works.leagueservice.config"})
public class ApplicationConfig {
}

package com.works.leagueservice.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author mali.sahin
 * @since 2019-06-26.
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.works.leagueservice.repository")
@EnableJpaAuditing
@EntityScan(basePackages = "com.works.leagueservice.domain")
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.works.leagueservice.mappingservice", "com.works.leagueservice.service"})
public class ApplicationConfig {
}

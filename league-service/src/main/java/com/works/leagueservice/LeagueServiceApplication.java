package com.works.leagueservice;

import com.works.leagueservice.config.ApplicationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ApplicationConfig.class })
public class LeagueServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeagueServiceApplication.class, args);
    }

}
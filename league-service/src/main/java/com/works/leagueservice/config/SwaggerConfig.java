package com.works.leagueservice.config;

import com.works.sharedlibrary.config.SwaggerBaseConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author mali.sahin
 * @since 2019-06-26.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends SwaggerBaseConfig {

    @Bean
    public Docket api() {
        return super.api();
    }

}

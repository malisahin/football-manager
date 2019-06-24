package com.works.playerservice.config;

import com.works.sharedlibrary.config.SwaggerBaseConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author mali.sahin
 * @since 6/24/19.
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig extends SwaggerBaseConfig {

  @Bean
  public Docket api() {
    return super.api();
  }

}

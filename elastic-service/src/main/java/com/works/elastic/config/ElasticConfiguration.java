package com.works.elastic.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @author mali.sahin
 * @since 2019-07-14.
 */
@Configuration
@EnableElasticsearchRepositories()
public class ElasticConfiguration {
}

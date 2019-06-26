package com.works.sharedlibrary.config;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;

/**
 * @author mali.sahin
 * @since 2019-06-26.
 */
@Configuration
public class DebugSpringConfiguration implements BeanPostProcessor {
    Logger log = (Logger) LoggerFactory.getLogger(this.getClass());

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.info("postProcessBeforeInitialization: bean(" + beanName + ")");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.info("postProcessAfterInitialization: bean(" + beanName + ")");
        return bean;
    }
}

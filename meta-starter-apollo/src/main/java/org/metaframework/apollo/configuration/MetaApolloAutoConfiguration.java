package org.metaframework.apollo.configuration;

import com.ctrip.framework.apollo.spring.config.ConfigPropertySourcesProcessor;
import com.ctrip.framework.apollo.spring.config.PropertySourcesConstants;
import com.ctrip.framework.apollo.spring.config.PropertySourcesProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author sven
 * Created on 2023/7/16 2:49 AM
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@ComponentScan(basePackages = "org.metaframework.apollo")
@ConditionalOnProperty(PropertySourcesConstants.APOLLO_BOOTSTRAP_ENABLED)
@ConditionalOnMissingBean(PropertySourcesProcessor.class)
public class MetaApolloAutoConfiguration {
    @Bean
    public ConfigPropertySourcesProcessor configPropertySourcesProcessor() {
        return new ConfigPropertySourcesProcessor();
    }
}

package org.metaframework.apollo.props;

import lombok.Data;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

import java.util.Set;

/**
 * @author sven
 * Created on 2023/7/16 2:51 PM
 */
@Data
@Configuration
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
@ConfigurationProperties(prefix = "apollo")
public class ApolloProperties {
    /**
     * 要监听的key全名，支持多个
     */
    private Set<String> interestedKeys;

    /**
     * 要监听的key前缀, 支持多个
     */
    private Set<String> interestedKeyPrefixes;
}
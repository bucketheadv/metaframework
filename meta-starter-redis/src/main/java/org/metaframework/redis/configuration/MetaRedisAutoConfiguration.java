package org.metaframework.redis.configuration;

import lombok.extern.slf4j.Slf4j;
import org.metaframework.redis.props.RedisProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.lang.NonNull;

/**
 * @author sven
 * Created on 2023/7/19 3:55 PM
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@ComponentScan(basePackages = "org.metaframework.redis")
public class MetaRedisAutoConfiguration implements EnvironmentAware {
    private Environment environment;

    @Bean
    public RedisProperties redisProperties() {
        try {
            return Binder.get(environment).bind("meta.redis", RedisProperties.class).get();
        } catch (Exception e) {
            log.error("RedisProperties绑定异常", e);
        }
        return null;
    }

    @Bean
    @ConditionalOnBean(RedisProperties.class)
    public MetaRedisRegistry metaRedisRegistry(RedisProperties redisProperties) {
        return new MetaRedisRegistry(redisProperties);
    }

    @Override
    public void setEnvironment(@NonNull Environment environment) {
        this.environment = environment;
    }
}

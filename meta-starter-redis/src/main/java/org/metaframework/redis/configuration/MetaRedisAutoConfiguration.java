package org.metaframework.redis.configuration;

import org.metaframework.redis.props.RedisProperties;
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
@Configuration(proxyBeanMethods = false)
@ComponentScan(basePackages = "org.metaframework.redis")
public class MetaRedisAutoConfiguration implements EnvironmentAware {
    private Environment environment;

    @Bean
    public RedisProperties redisProperties() {
        return Binder.get(environment).bind("meta.redis", RedisProperties.class).get();
    }

    @Bean
    public MetaRedisRegistry metaRedisRegistry(RedisProperties redisProperties) {
        return new MetaRedisRegistry(redisProperties);
    }

    @Override
    public void setEnvironment(@NonNull Environment environment) {
        this.environment = environment;
    }
}

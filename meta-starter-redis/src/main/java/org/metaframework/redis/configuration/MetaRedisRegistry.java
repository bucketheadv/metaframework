package org.metaframework.redis.configuration;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.metaframework.redis.client.JedisTemplate;
import org.metaframework.redis.client.config.DefaultJedisClientConfig;
import org.metaframework.redis.client.impl.DefaultJedisTemplate;
import org.metaframework.redis.props.RedisProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.lang.NonNull;
import redis.clients.jedis.*;

import java.util.Objects;

/**
 * @author sven
 * Created on 2023/7/19 3:56 PM
 */
@Slf4j
@AllArgsConstructor
public class MetaRedisRegistry implements BeanDefinitionRegistryPostProcessor {

    private RedisProperties redisProperties;

    @Override
    public void postProcessBeanDefinitionRegistry(@NonNull BeanDefinitionRegistry registry) throws BeansException {
        String primary = redisProperties.getPrimary();
        for (String templateName : redisProperties.getTemplates().keySet()) {
            boolean isPrimary = Objects.equals(primary, templateName);
            RedisProperties.RedisNode redisNode = redisProperties.getTemplates().get(templateName);

            BeanDefinition jedisPoolConfigBeanDefinition = BeanDefinitionBuilder.rootBeanDefinition(JedisPoolConfig.class)
                    .setPrimary(isPrimary)
                    .addPropertyValue("testOnCreate", redisNode.isTestOnCreate())
                    .addPropertyValue("testOnReturn", redisNode.isTestOnReturn())
                    .addPropertyValue("testOnBorrow", redisNode.isTestOnBorrow())
                    .addPropertyValue("testWhileIdle", redisNode.isTestWhileIdle())
                    .addPropertyValue("jmxEnabled", redisNode.isJmxEnabled())
                    .addPropertyValue("jmxNameBase", redisNode.getJmxNameBase())
                    .addPropertyValue("jmxNamePrefix", redisNode.getJmxNamePrefix())
                    .addPropertyValue("maxIdle", redisNode.getMaxIdle())
                    .addPropertyValue("minIdle", redisNode.getMinIdle())
                    .addPropertyValue("maxTotal", redisNode.getMaxTotal())
                    .getBeanDefinition();

            String jedisPoolConfigBeanName = templateName + JedisPoolConfig.class.getSimpleName();
            registry.registerBeanDefinition(jedisPoolConfigBeanName, jedisPoolConfigBeanDefinition);

            BeanDefinition hostAndPortBeanDefinition = BeanDefinitionBuilder.rootBeanDefinition(HostAndPort.class)
                    .setPrimary(isPrimary)
                    .addConstructorArgValue(redisNode.getHost())
                    .addConstructorArgValue(redisNode.getPort())
                    .getBeanDefinition();
            String hostAndPortBeanName = templateName + HostAndPort.class.getSimpleName();
            registry.registerBeanDefinition(hostAndPortBeanName, hostAndPortBeanDefinition);

            BeanDefinition clientConfigBeanDefinition = BeanDefinitionBuilder.rootBeanDefinition(DefaultJedisClientConfig.class)
                    .setPrimary(isPrimary)
                    .addConstructorArgValue(redisNode.getDb())
                    .addPropertyValue("user", redisNode.getUsername())
                    .addPropertyValue("password", redisNode.getPassword())
                    .getBeanDefinition();
            String clientConfigBeanName = templateName + JedisClientConfig.class.getSimpleName();
            registry.registerBeanDefinition(clientConfigBeanName, clientConfigBeanDefinition);

            BeanDefinition jedisPoolBeanDefinition = BeanDefinitionBuilder.rootBeanDefinition(JedisPool.class)
                    .setPrimary(isPrimary)
                    .addConstructorArgReference(jedisPoolConfigBeanName)
                    .addConstructorArgReference(hostAndPortBeanName)
                    .addConstructorArgReference(clientConfigBeanName)
                    .getBeanDefinition();
            String jedisPoolBeanName = templateName + "JedisPool";
            registry.registerBeanDefinition(jedisPoolBeanName, jedisPoolBeanDefinition);

            BeanDefinition jedisTemplateBeanDefinition = BeanDefinitionBuilder.rootBeanDefinition(DefaultJedisTemplate.class)
                    .setPrimary(isPrimary)
                    .addConstructorArgReference(jedisPoolBeanName)
                    .getBeanDefinition();
            String jedisTemplateBeanName = templateName + JedisTemplate.class.getSimpleName();
            registry.registerBeanDefinition(jedisTemplateBeanName, jedisTemplateBeanDefinition);
            log.info("注册Bean: {}", jedisTemplateBeanName);
            if (isPrimary) {
                log.info("Bean: {} 被设置为Primary", jedisTemplateBeanName);
            }
        }
    }

    @Override
    public void postProcessBeanFactory(@NonNull ConfigurableListableBeanFactory beanFactory) throws BeansException {
    }
}

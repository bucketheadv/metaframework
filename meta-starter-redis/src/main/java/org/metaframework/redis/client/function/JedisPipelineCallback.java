package org.metaframework.redis.client.function;

import redis.clients.jedis.Pipeline;

/**
 * @author sven
 * Created on 2023/7/20 2:36 AM
 */
@FunctionalInterface
public interface JedisPipelineCallback {
    void apply(Pipeline pipeline);
}
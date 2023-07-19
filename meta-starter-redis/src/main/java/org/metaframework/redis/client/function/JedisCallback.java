package org.metaframework.redis.client.function;

import redis.clients.jedis.Jedis;

/**
 * @author sven
 * Created on 2023/7/19 5:16 PM
 */
@FunctionalInterface
public interface JedisCallback<T> {
    T apply(Jedis jedis);
}
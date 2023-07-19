package org.metaframework.redis.client.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.metaframework.redis.client.JedisTemplate;
import org.metaframework.redis.client.function.JedisCallback;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author sven
 * Created on 2023/7/19 4:06 PM
 */
@Slf4j
@AllArgsConstructor
public class DefaultJedisTemplate implements JedisTemplate {

    private JedisPool jedisPool;

    @Override
    public String get(String key) {
        return tryGetResource(jedis -> jedis.get(key));
    }

    @Override
    public String set(String key, String value) {
        return tryGetResource(jedis -> jedis.set(key, value));
    }

    private <T> T tryGetResource(JedisCallback<T> callback) {
        try (Jedis jedis = jedisPool.getResource()){
            return callback.apply(jedis);
        } catch (Exception e) {
            log.error("tryGetResource error: ", e);
            throw new RuntimeException(e);
        }
    }
}

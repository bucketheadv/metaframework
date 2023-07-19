package org.metaframework.redis.client;

/**
 * @author sven
 * Created on 2023/7/19 3:54 PM
 */
public interface JedisTemplate {
    String get(String key);

    String set(String key, String value);
}

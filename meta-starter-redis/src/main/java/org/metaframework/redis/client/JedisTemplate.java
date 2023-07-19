package org.metaframework.redis.client;

import org.metaframework.redis.client.function.JedisPipelineCallback;
import redis.clients.jedis.commands.*;

import java.io.Closeable;
import java.util.List;

/**
 * @author sven
 * Created on 2023/7/19 3:54 PM
 */
public interface JedisTemplate extends ServerCommands, DatabaseCommands, JedisCommands, JedisBinaryCommands, ControlCommands, ControlBinaryCommands, ClusterCommands, ModuleCommands, GenericControlCommands, SentinelCommands, Closeable {
    List<Object> doInPipeline(JedisPipelineCallback callback);
}

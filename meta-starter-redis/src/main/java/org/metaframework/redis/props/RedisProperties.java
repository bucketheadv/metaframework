package org.metaframework.redis.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * @author sven
 * Created on 2023/7/19 4:03 PM
 */
@Data
@ConfigurationProperties(prefix = "meta.redis")
public class RedisProperties {
    /**
     * 默认实例
     */
    private String primary = "default";

    /**
     * 配置模板
     */
    private Map<String, RedisNode> templates;

    @Data
    public static class RedisNode {
        private String host = "localhost";

        private int port = 6379;

        private String username;

        private String password;

        private int db = 0;

        /**
         * 线程池最大线程数
         */
        private int maxTotal = 8;
        /**
         * 线程池最大闲置线程数
         */
        private int maxIdle = 8;
        /**
         * 线程池最小闲置线程数
         */
        private int minIdle = 0;

        /**
         * 是否在创建时测试该连接是否还可用
         */
        private boolean testOnCreate = true;
        /**
         * 是否在借出线程时测试该连接是否还可用
         */
        private boolean testOnBorrow = true;
        /**
         * 是否在线程池回收线程时测试该连接是否还可用
         */
        private boolean testOnReturn = false;
        /**
         * 是否在闲置时测试该连接是否还可用
         */
        private boolean testWhileIdle = true;

        /**
         * 是否启用jmx
         */
        private boolean jmxEnabled;
        /**
         * jmx名称前缀
         */
        private String jmxNamePrefix;
        /**
         * jmx基本名称
         */
        private String jmxNameBase;
    }
}

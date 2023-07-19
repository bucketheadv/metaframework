package org.metaframework.redis.client.config;

import redis.clients.jedis.HostAndPortMapper;
import redis.clients.jedis.JedisClientConfig;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocketFactory;
import java.util.Objects;

/**
 * @author sven
 * Created on 2023/7/19 7:16 PM
 */
public class DefaultJedisClientConfig implements JedisClientConfig {
    private int connectionTimeoutMillis;
    private int socketTimeoutMillis;
    private int blockingSocketTimeoutMillis;
    private String user;
    private volatile String password;
    private final int database;
    private String clientName;
    private boolean ssl;
    private SSLSocketFactory sslSocketFactory;
    private SSLParameters sslParameters;
    private HostnameVerifier hostnameVerifier;
    private HostAndPortMapper hostAndPortMapper;

    public DefaultJedisClientConfig(int database) {
        this.database = database;
    }

    @Override
    public int getConnectionTimeoutMillis() {
        return this.connectionTimeoutMillis;
    }

    @Override
    public int getSocketTimeoutMillis() {
        return this.socketTimeoutMillis;
    }

    @Override
    public int getBlockingSocketTimeoutMillis() {
        return this.blockingSocketTimeoutMillis;
    }

    @Override
    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public synchronized void updatePassword(String password) {
        if (!Objects.equals(this.password, password)) {
            this.password = password;
        }

    }

    @Override
    public int getDatabase() {
        return this.database;
    }

    @Override
    public String getClientName() {
        return this.clientName;
    }

    @Override
    public boolean isSsl() {
        return this.ssl;
    }

    @Override
    public SSLSocketFactory getSslSocketFactory() {
        return this.sslSocketFactory;
    }

    @Override
    public SSLParameters getSslParameters() {
        return this.sslParameters;
    }

    @Override
    public HostnameVerifier getHostnameVerifier() {
        return this.hostnameVerifier;
    }

    @Override
    public HostAndPortMapper getHostAndPortMapper() {
        return this.hostAndPortMapper;
    }
}

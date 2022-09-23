package com.icboluo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Configuration
@Profile("simple")
public class EmbededRedisConfiguration {

    private final RedisServer redisServer;

    public EmbededRedisConfiguration(@Value("${spring.redis.port}") final int redisPort) {
        this.redisServer = RedisServer.builder().port(redisPort).setting("maxmemory 256m").build();
    }

    @PostConstruct
    public void startRedis() {
        this.redisServer.start();
    }

    @PreDestroy
    public void stopRedis() {
        this.redisServer.stop();
    }
}

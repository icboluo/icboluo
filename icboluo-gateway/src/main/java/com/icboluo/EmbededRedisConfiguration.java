package com.icboluo;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import redis.embedded.RedisServer;

/**
 * @author icboluo
 * @since 2023-11-12 14:06
 */
@Configuration
@Profile("simple")
@Slf4j
public class EmbededRedisConfiguration {

    private final RedisServer redisServer;

    public EmbededRedisConfiguration(@Value("${spring.data.redis.port}") final int redisPort) {
        this.redisServer = RedisServer.builder().port(redisPort).setting("maxmemory 256m").build();
    }

    @PostConstruct
    public void startRedis() {
        this.redisServer.start();
        log.warn("redis service start");
    }

    @PreDestroy
    public void stopRedis() {
        this.redisServer.stop();
        log.warn("redis service stop");
    }
}

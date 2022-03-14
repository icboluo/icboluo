package com.icboluo.common.redis;

import com.icboluo.function.Procedure;
import lombok.SneakyThrows;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @author icboluo
 */
@Component
public class RedisLock extends AbstractRedis<Object> {

    private long lockExpire;

    /**
     * 常用的分布式锁加强版
     * 最终加强分布式锁
     *
     * @param key key值
     * @return 是否获取到
     */
    public boolean lock(String key) {
        byte[] lockBytes = key.getBytes(StandardCharsets.UTF_8);
        Boolean execute = redisTemplate.execute((RedisCallback<Boolean>) redisConn -> {
            long expireAt = System.currentTimeMillis() + lockExpire + 1;
            Boolean acquire = redisConn.setNX(lockBytes, String.valueOf(expireAt).getBytes());
            if (Objects.equals(acquire, Boolean.TRUE)) {
                return true;
            }
            byte[] value = redisConn.get(lockBytes);
            if (Objects.nonNull(value) && value.length > 0) {
                long expireTime = Long.parseLong(new String(value));
                if (expireTime < System.currentTimeMillis()) {
                    // 如果锁已经过期
                    byte[] oldValue = redisConn.getSet(lockBytes, String.valueOf(System.currentTimeMillis() + lockExpire + 1).getBytes());
                    // 防止死锁
                    return Long.parseLong(new String(oldValue)) < System.currentTimeMillis();
                }
            }
            return false;
        });
        return Objects.equals(execute, Boolean.TRUE);
    }

    @SneakyThrows
    public void queueHand(String key, int time, Procedure procedure) {
        for (int i = 0; i < time; i++) {
            if (!lock(key)) {
                Thread.sleep(1000L);
                continue;
            }
            try {
                procedure.run();
            }finally {
                del(key);
            }
        }
    }
}
package com.icboluo.common.redis;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.BoundKeyOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.support.atomic.RedisAtomicDouble;
import org.springframework.data.redis.support.atomic.RedisAtomicInteger;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author icboluo
 */
@Component
@Slf4j
@SuppressWarnings("unused")
public class RedisString<T> extends AbstractRedis<T> {

    @Resource
    private ValueOperations<String, T> valueOperations;

    @SuppressWarnings("all")
    private void example() {
        ValueOperations<String, T> valueOperations = redisTemplate.opsForValue();
    }

    /**
     * 获取缓存
     *
     * @param key 键
     * @return 值
     */
    public Optional<T> get(String key) {
        return key == null ? Optional.empty() : Optional.ofNullable(valueOperations.get(key));
    }

    public T getOfNullAble(String key) {
        return this.get(key).orElse(null);
    }

    /**
     * 添加缓存
     *
     * @param key   键
     * @param value 值
     */
    public void set(String key, T value) {
        valueOperations.set(key, value);
    }

    /**
     * 添加缓存并设置过期时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     */
    public void set(String key, T value, long time) {
        set(key, value, time, TimeUnit.SECONDS);
    }

    public void set(String key, T value, long time, TimeUnit timeUnit) {
        if (time > 0) {
            valueOperations.set(key, value, time, timeUnit);
        } else {
            set(key, value);
        }
    }

    public void set(Map<String, T> map) {
        valueOperations.multiSet(map);
    }

    public Integer increment(String key) {
        return incrementExpireAt(key, Integer.class, 1, null);
    }

    public <G extends Number> G increment(String key, G delta) {
        return incrementExpireAt(key, (Class<G>) delta.getClass(), delta, null);
    }

    public Integer incrementExpireAt(String key, LocalDateTime localDateTime) {
        return incrementExpireAt(key, Integer.class, 1, localDateTime);
    }

    public <G extends Number> G incrementExpireAt(String key, G delta, LocalDateTime localDateTime) {
        return incrementExpireAt(key, (Class<G>) delta.getClass(), delta, localDateTime);
    }

    /**
     * 递增操作
     * valueOperations.increment(key, delta);
     * 过期时间是可以设置的，可是为什么有的情况下，用atomic的时候，会在redis中找不到键值
     *
     * @param key   键
     * @param cla   要转化的数据类型
     * @param delta 递增因子
     * @param <G>   泛型类型
     * @return 递增之后的值
     */
    public <G extends Number> G incrementExpireAt(String key, Class<G> cla, G delta, LocalDateTime localDateTime) {
        if (delta.intValue() < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        if (cla == Long.class) {
            RedisAtomicLong redisAtomicLong = new RedisAtomicLong(key, Objects.requireNonNull(redisTemplate.getConnectionFactory()));
            expireAt(redisAtomicLong, localDateTime);
            return cla.cast(redisAtomicLong.addAndGet(delta.longValue()));
        } else if (cla == Integer.class) {
            RedisAtomicInteger redisAtomicInteger = new RedisAtomicInteger(key, Objects.requireNonNull(redisTemplate.getConnectionFactory()));
            expireAt(redisAtomicInteger, localDateTime);
            return cla.cast(redisAtomicInteger.addAndGet(delta.intValue()));
        } else if (cla == Double.class) {
            RedisAtomicDouble redisAtomicDouble = new RedisAtomicDouble(key, Objects.requireNonNull(redisTemplate.getConnectionFactory()));
            expireAt(redisAtomicDouble, localDateTime);
            return cla.cast(redisAtomicDouble.addAndGet(delta.doubleValue()));
        }
        log.error("redis decrease error, delta cla not belong to long,integer and double");
        return null;
    }


    public Integer decrease(String key) {
        return decreaseExpireAt(key, Integer.class, 1, null);
    }

    public <G extends Number> G decrease(String key, G g) {
        return decreaseExpireAt(key, (Class<G>) g.getClass(), g, null);
    }

    public Integer decreaseExpireAt(String key, LocalDateTime localDateTime) {
        return decreaseExpireAt(key, Integer.class, 1, localDateTime);
    }

    public <G extends Number> G decreaseExpireAt(String key, G delta, LocalDateTime localDateTime) {
        return decreaseExpireAt(key, (Class<G>) delta.getClass(), delta, localDateTime);
    }

    /**
     * 递减操作
     * valueOperations.increment(key, -delta);
     *
     * @param key   键
     * @param cla   要转化的数据类型
     * @param delta 递减因子
     * @param <G>   泛型类型
     * @return 递减之后的值
     */
    public <G extends Number> G decreaseExpireAt(String key, Class<G> cla, G delta, LocalDateTime localDateTime) {
        if (delta.intValue() < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        if (cla == Long.class) {
            RedisAtomicLong redisAtomicLong = new RedisAtomicLong(key, Objects.requireNonNull(redisTemplate.getConnectionFactory()));
            expireAt(redisAtomicLong, localDateTime);
            return cla.cast(redisAtomicLong.addAndGet(-delta.longValue()));
        } else if (cla == Integer.class) {
            RedisAtomicInteger redisAtomicInteger = new RedisAtomicInteger(key, Objects.requireNonNull(redisTemplate.getConnectionFactory()));
            expireAt(redisAtomicInteger, localDateTime);
            return cla.cast(redisAtomicInteger.addAndGet(-delta.intValue()));
        } else if (cla == Double.class) {
            RedisAtomicDouble redisAtomicDouble = new RedisAtomicDouble(key, Objects.requireNonNull(redisTemplate.getConnectionFactory()));
            expireAt(redisAtomicDouble, localDateTime);
            return cla.cast(redisAtomicDouble.addAndGet(-delta.doubleValue()));
        }
        log.error("redis decrease error, delta cla not belong to long,integer and double");
        return null;
    }

    private void expireAt(BoundKeyOperations<String> key, LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return;
        }
        Duration between = Duration.between(LocalDateTime.now(), localDateTime);
//        过期时间取自redis,如果和服务时间不一致会出现问题,所以暂时不要使用expireAt函数
        key.expire(between.getSeconds(), TimeUnit.SECONDS);
    }
}

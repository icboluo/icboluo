package com.icboluo.util.redis;

import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.support.atomic.RedisAtomicDouble;
import org.springframework.data.redis.support.atomic.RedisAtomicInteger;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author icboluo
 */
@Component
@SuppressWarnings("unused")
public class StringRedis<T> extends AbstractRedis<T> {

    @Resource
    private ValueOperations<String, T> valueOperations;

    @SuppressWarnings("unused")
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
     * @return true成功 false失败
     */
    public boolean set(String key, T value) {
        try {
            valueOperations.set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 添加缓存并设置过期时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public boolean set(String key, T value, long time) {
        return set(key, value, time, TimeUnit.SECONDS);
    }

    public boolean set(String key, T value, long time, TimeUnit timeUnit) {
        try {
            if (time > 0) {
                valueOperations.set(key, value, time, timeUnit);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Integer increment(String key) {
        return increment(key, 1, Integer.class);
    }

    public <G extends Number> G increment(String key, Class<G> g) {
        return increment(key, g.cast(1), g);
    }

    /**
     * 递增操作
     * valueOperations.increment(key, delta);
     *
     * @param key   键
     * @param delta 递增因子
     * @param g     要转化的数据类型
     * @param <G>   泛型类型
     * @return 递增之后的值
     */
    public <G extends Number> G increment(String key, G delta, Class<G> g) {
        if (delta.intValue() < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        if (g == Long.class) {
            RedisAtomicLong redisAtomicLong = new RedisAtomicLong(key, Objects.requireNonNull(redisTemplate.getConnectionFactory()));
            return g.cast(redisAtomicLong.addAndGet((Long) delta));
        } else if (g == Integer.class) {
            RedisAtomicInteger redisAtomicInteger = new RedisAtomicInteger(key, Objects.requireNonNull(redisTemplate.getConnectionFactory()));
            return g.cast(redisAtomicInteger.addAndGet((Integer) delta));
        } else if (g == Double.class) {
            RedisAtomicDouble redisAtomicDouble = new RedisAtomicDouble(key, Objects.requireNonNull(redisTemplate.getConnectionFactory()));
            return g.cast(redisAtomicDouble.addAndGet((Double) delta));
        }
        return null;
    }

    public Integer decrease(String key) {
        return decrease(key, 1, Integer.class);
    }

    public <G extends Number> G decrease(String key, Class<G> g) {
        return decrease(key, g.cast(1), g);
    }

    /**
     * 递减操作
     * valueOperations.increment(key, -delta);
     *
     * @param key   键
     * @param delta 递减因子
     * @param g     要转化的数据类型
     * @param <G>   泛型类型
     * @return 递减之后的值
     */
    public <G extends Number> G decrease(String key, G delta, Class<G> g) {
        if (delta.intValue() < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        if (g == Long.class) {
            RedisAtomicLong redisAtomicLong = new RedisAtomicLong(key, Objects.requireNonNull(redisTemplate.getConnectionFactory()));
            return g.cast(redisAtomicLong.addAndGet(-delta.longValue()));
        } else if (g == Integer.class) {
            RedisAtomicInteger redisAtomicInteger = new RedisAtomicInteger(key, Objects.requireNonNull(redisTemplate.getConnectionFactory()));
            return g.cast(redisAtomicInteger.addAndGet(-delta.intValue()));
        } else if (g == Double.class) {
            RedisAtomicDouble redisAtomicDouble = new RedisAtomicDouble(key, Objects.requireNonNull(redisTemplate.getConnectionFactory()));
            return g.cast(redisAtomicDouble.addAndGet(-delta.doubleValue()));
        }
        return null;
    }
}

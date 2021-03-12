package com.icboluo.util.redis;

import com.icboluo.util.DateHelper;
import org.springframework.data.redis.core.BoundKeyOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.support.atomic.RedisAtomicDouble;
import org.springframework.data.redis.support.atomic.RedisAtomicInteger;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * @author icboluo
 */
@Component
@SuppressWarnings("unused")
public class RedisString<T> extends AbstractRedis<T> {

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
        return incrementExpireAt(key, Integer.class, null);
    }

    public <G extends Number> G increment(String key, Class<G> g) {
        return incrementExpireAt(key, g, null);
    }

    public Integer incrementExpireAt(String key, LocalDateTime localDateTime) {
        return incrementExpireAt(key, Integer.class, localDateTime);
    }

    public <G extends Number> G incrementExpireAt(String key, Class<G> g, LocalDateTime localDateTime) {
        Consumer<BoundKeyOperations<String>> expireAt = null;
        if (localDateTime != null) {
            expireAt = (operation) -> operation.expireAt(DateHelper.localDateTimeToDate(localDateTime));
        }
        return incrementExpireAt(key, g.cast(1L), g, expireAt);
    }

    /**
     * 递增操作
     * valueOperations.increment(key, delta);
     * 过期时间是可以设置的，可是为什么有的情况下，用atomic的时候，会在redis中找不到键值
     *
     * @param key   键
     * @param delta 递增因子
     * @param g     要转化的数据类型
     * @param <G>   泛型类型
     * @return 递增之后的值
     */
    public <G extends Number> G incrementExpireAt(String key, G delta, Class<G> g, Consumer<BoundKeyOperations<String>> expireAt) {
        if (delta.intValue() < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        if (g == Long.class) {
            RedisAtomicLong redisAtomicLong = new RedisAtomicLong(key, Objects.requireNonNull(redisTemplate.getConnectionFactory()));
            if (expireAt != null) {
                expireAt.accept(redisAtomicLong);
            }
            return g.cast(redisAtomicLong.addAndGet((Long) delta));
        } else if (g == Integer.class) {
            RedisAtomicInteger redisAtomicInteger = new RedisAtomicInteger(key, Objects.requireNonNull(redisTemplate.getConnectionFactory()));
            if (expireAt != null) {
                expireAt.accept(redisAtomicInteger);
            }
            return g.cast(redisAtomicInteger.addAndGet((Integer) delta));
        } else if (g == Double.class) {
            RedisAtomicDouble redisAtomicDouble = new RedisAtomicDouble(key, Objects.requireNonNull(redisTemplate.getConnectionFactory()));
            if (expireAt != null) {
                expireAt.accept(redisAtomicDouble);
            }
            return g.cast(redisAtomicDouble.addAndGet((Double) delta));
        }
        return null;
    }

    public Integer decrease(String key) {
        return decrease(key, null);
    }

    public <G extends Number> G decrease(String key, Class<G> g) {
        return decreaseExpireAt(key, g, null);
    }

    public Integer decreaseExpireAt(String key, LocalDateTime localDateTime) {
        return decreaseExpireAt(key, Integer.class, localDateTime);
    }

    public <G extends Number> G decreaseExpireAt(String key, Class<G> g, LocalDateTime localDateTime) {
        Consumer<BoundKeyOperations<String>> expireAt = null;
        if (localDateTime != null) {
            expireAt = (operation) -> operation.expireAt(DateHelper.localDateTimeToDate(localDateTime));
        }
        return decreaseExpireAt(key, g.cast(1), g, expireAt);
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
    public <G extends Number> G decreaseExpireAt(String key, G delta, Class<G> g, Consumer<BoundKeyOperations<String>> expireAt) {
        if (delta.intValue() < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        if (g == Long.class) {
            RedisAtomicLong redisAtomicLong = new RedisAtomicLong(key, Objects.requireNonNull(redisTemplate.getConnectionFactory()));
            if (expireAt != null) {
                expireAt.accept(redisAtomicLong);
            }
            return g.cast(redisAtomicLong.addAndGet(-delta.longValue()));
        } else if (g == Integer.class) {
            RedisAtomicInteger redisAtomicInteger = new RedisAtomicInteger(key, Objects.requireNonNull(redisTemplate.getConnectionFactory()));
            if (expireAt != null) {
                expireAt.accept(redisAtomicInteger);
            }
            return g.cast(redisAtomicInteger.addAndGet(-delta.intValue()));
        } else if (g == Double.class) {
            RedisAtomicDouble redisAtomicDouble = new RedisAtomicDouble(key, Objects.requireNonNull(redisTemplate.getConnectionFactory()));
            if (expireAt != null) {
                expireAt.accept(redisAtomicDouble);
            }
            return g.cast(redisAtomicDouble.addAndGet(-delta.doubleValue()));
        }
        return null;
    }
}

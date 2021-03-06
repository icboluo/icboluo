package com.icboluo.util.redis;

import com.icboluo.util.DateHelper;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;


/**
 * 通用操作工具
 * 与redis操作属于第3方操作，需要支持序列化
 * idea的debug提示属于toString提示
 * 父类序列化的时候子类属性也会被序列化
 * 父类toString子类不会toString
 * redis 的所有键都是String
 *
 * @author icboluo
 * @date 2020/12/17 21:27
 */
@SuppressWarnings("unused")
public abstract class AbstractRedis<T> {
    /**
     * todo 这个直接用list redis 去调用的话会是空，应该怎么处理呢
     */
    @Resource
    public RedisTemplate<String, T> redisTemplate;


    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @return 过期时间是否设置成功
     */
    public boolean expire(String key, long time) {
        return expire(key, time, TimeUnit.SECONDS);
    }

    public Boolean expire(String key, long time, TimeUnit timeUnit) {
        if (time > 0) {
            return redisTemplate.expire(key, time, timeUnit);
        }
        return true;
    }

    public Boolean expireAt(String key, LocalDateTime localDateTime) {
        if (localDateTime.compareTo(LocalDateTime.now()) > 0) {
            return redisTemplate.expireAt(key, DateHelper.localDateTimeToDate(localDateTime));
        }
        return true;
    }

    /**
     * 根据key 获取过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    public Boolean containsKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 删除缓存
     *
     * @param key 可以传一个值 或多个
     */
    public Long del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                Boolean delete = redisTemplate.delete(key[0]);
                return Boolean.TRUE.equals(delete) ? 1L : 0;
            } else {
                return redisTemplate.delete(Arrays.asList(key));
            }
        }
        return null;
    }

    public Long del(Collection<String> keys) {
        return redisTemplate.delete(keys);
    }

    /**
     * Cache Aside Pattern（旁路缓存模式）
     *
     * @param cacheOperation 缓存操作
     * @param dbOperation    数据库操作
     * @param cacheCallBack  回调函数
     * @param <G>            返回值类型
     * @return 要查询数据
     */
    public <G> G rCap(Supplier<G> cacheOperation, Supplier<G> dbOperation, Consumer<G> cacheCallBack) {
        G g = null;
        try {
            g = cacheOperation.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (ObjectUtils.isEmpty(g)) {
//            如果找不到，先从db中读出来写到缓存中
            g = dbOperation.get();
            cacheCallBack.accept(g);
        }
        return g;
    }

    /**
     * Cache Aside Pattern（旁路缓存模式）
     *
     * @param dbUpdate 更新数据库操作
     * @param cacheDel 缓存删除操作
     */
    public void wCap(Procedure dbUpdate, Procedure cacheDel) {
        dbUpdate.run();
        cacheDel.run();
    }

    public void type(String key) {
        DataType type = redisTemplate.type(key);
    }

    /**
     * keys命令大数据亮容易卡死
     *
     * @param commonPreKey
     * @return
     */
    public Set<String> keys(String commonPreKey) {
        return redisTemplate.keys(commonPreKey + ":" + "*");
    }

    public Long delKeys(String... commonPreKey) {
        Set<String> keys = Arrays.stream(commonPreKey)
                .map(this::keys)
                .flatMap(Collection::stream)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        return del(keys);
    }
}

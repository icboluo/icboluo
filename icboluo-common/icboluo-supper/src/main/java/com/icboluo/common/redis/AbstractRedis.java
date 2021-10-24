package com.icboluo.common.redis;


import com.icboluo.function.Procedure;
import com.icboluo.util.DateHelper;
import lombok.NonNull;
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
     * TODO 这个直接用list redis 去调用的话会是空，应该怎么处理呢
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
        if (time <= 0) {
            return true;
        }
        return redisTemplate.expire(key, time, timeUnit);
    }

    public Boolean expireAt(String key, LocalDateTime localDateTime) {
        if (localDateTime.compareTo(LocalDateTime.now()) <= 0) {
            return true;
        }
        return redisTemplate.expireAt(key, DateHelper.localDateTimeToDate(localDateTime));
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
     * 查询key的生命周期
     *
     * @param key      键 不能为null
     * @param timeUnit 时间单位
     * @return 时间(秒) 返回0代表为永久有效
     */
    public Long getKeyExpire(String key, TimeUnit timeUnit) {
        return redisTemplate.getExpire(key, timeUnit);
    }


    /**
     * 判断key是否存在 existsKey
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
        if (key == null || key.length == 0) {
            return null;
        }
        if (key.length == 1) {
            Boolean delete = redisTemplate.delete(key[0]);
            return Boolean.TRUE.equals(delete) ? 1L : 0;
        } else {
            return redisTemplate.delete(Arrays.asList(key));
        }
    }

    public Long del(@NonNull Collection<String> keys) {
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
     * @param commonPreKey 公共的key前缀
     * @return 获得所有匹配的key
     */
    public Set<String> keys(String commonPreKey) {
        return redisTemplate.keys(commonPreKey + ":" + "*");
    }

    public long delKeys(String... commonPreKey) {
        Set<String> keys = Arrays.stream(commonPreKey)
                .map(this::keys)
                .flatMap(Collection::stream)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        return del(keys);
    }

    /**
     * 重名名key，如果newKey已经存在，则newKey的原值被覆盖
     *
     * @param oldKey 旧key
     * @param newKey 新key
     */
    public void renameKey(String oldKey, String newKey) {
        redisTemplate.rename(oldKey, newKey);
    }

    /**
     * newKey不存在时才重命名
     *
     * @param oldKey 旧key
     * @param newKey 新key
     * @return 修改成功返回true
     */
    public boolean renameKeyNotExist(String oldKey, String newKey) {
        return Boolean.TRUE.equals(redisTemplate.renameIfAbsent(oldKey, newKey));
    }

    /**
     * 将key设置为永久有效
     *
     * @param key key
     */
    public void persistKey(String key) {
        redisTemplate.persist(key);
    }
}

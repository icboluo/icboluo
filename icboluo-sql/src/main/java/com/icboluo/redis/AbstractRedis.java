package com.icboluo.redis;

import com.icboluo.util.DateHelper;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Supplier;


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

    @Resource
    RedisTemplate<String, T> redisTemplate;


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

    public boolean expire(String key, long time, TimeUnit timeUnit) {
        try {
            if (time > 0) {
                Boolean b = redisTemplate.expire(key, time, timeUnit);
                return Boolean.TRUE.equals(b);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean expireAt(String key, LocalDateTime localDateTime) {
        try {
            if (localDateTime.compareTo(LocalDateTime.now()) > 0) {
                Boolean b = redisTemplate.expireAt(key, DateHelper.localDateTimeToDate(localDateTime));
                return Boolean.TRUE.equals(b);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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
    public boolean containsKey(String key) {
        try {
            Boolean b = redisTemplate.hasKey(key);
            return Boolean.TRUE.equals(b);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除缓存
     *
     * @param key 可以传一个值 或多个
     */
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(Arrays.asList(key));
            }
        }
    }

    public void del(Collection<String> keys) {
        redisTemplate.delete(keys);
    }

    /**
     * todo 其他更新缓存策略
     * Read/Write Through Pattern 读写穿透
     * 服务端把 cache 视为主要数据存储，从中读取数据并将数据写入其中
     *
     * @param cacheOperation 缓存操作
     * @param dbOperation    数据库操作
     * @param cacheCallBack  回调函数
     * @param <G>            返回值类型
     * @return 要查询数据
     */
    public <G> G rtp(Supplier<G> cacheOperation, Supplier<G> dbOperation, Consumer<G> cacheCallBack) {
        G g = null;
        try {
            g = cacheOperation.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (g == null) {
//            如果找不到，先从db中读出来写到缓存中
            g = dbOperation.get();
            cacheCallBack.accept(g);
        }
        return g;
    }
}

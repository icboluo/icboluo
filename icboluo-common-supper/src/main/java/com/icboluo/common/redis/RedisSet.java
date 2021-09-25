package com.icboluo.common.redis;

import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;

/**
 * SET类型相关操作
 *
 * @author icboluo
 */
@Component
public class RedisSet<T> extends AbstractRedis<T> {

    @Resource
    private SetOperations<String, T> setOperations;

    @SuppressWarnings("all")
    private void example() {
        SetOperations<String, T> setOperations = redisTemplate.opsForSet();
    }


    /**
     * 根据key获取Set中的所有值
     *
     * @param key 键
     * @return
     */
    public Set<T> get(String key) {
        return setOperations.members(key);
    }

    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   键
     * @param value 值
     * @return true 存在 false不存在
     */
    public Boolean containsKey(String key, Object value) {
        return setOperations.isMember(key, value);
    }

    /**
     * 将数据放入set缓存 添加一个SET缓存
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public Long add(String key, T... values) {
        return setOperations.add(key, values);
    }

    /**
     * 将set数据放入缓存 添加一个SET缓存并设置过期时间
     *
     * @param key    键
     * @param time   时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public Long add(String key, long time, T... values) {
        Long count = setOperations.add(key, values);
        if (time > 0) {
            super.expire(key, time);
        }
        return count;
    }

    /**
     * 获取set缓存的长度
     *
     * @param key 键
     * @return
     */
    public Long size(String key) {
        return setOperations.size(key);
    }

    /**
     * 移除值为value的 移除指定key的缓存
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    public Long setRemove(String key, T... values) {
        return setOperations.remove(key, values);
    }
}

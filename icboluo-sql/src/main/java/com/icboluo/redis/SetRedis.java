package com.icboluo.redis;

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
public class SetRedis<T> extends AbstractRedis<T> {

    @Resource
    private SetOperations<String, T> setOperations;

    @SuppressWarnings("unused")

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
        try {
            return setOperations.members(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   键
     * @param value 值
     * @return true 存在 false不存在
     */
    public Boolean sHasKey(String key, Object value) {
        try {
            return setOperations.isMember(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将数据放入set缓存 添加一个SET缓存
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public Long set(String key, T... values) {
        try {
            return setOperations.add(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    /**
     * 将set数据放入缓存 添加一个SET缓存并设置过期时间
     *
     * @param key    键
     * @param time   时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public Long sSetAndTime(String key, long time, T... values) {
        try {
            Long count = setOperations.add(key, values);
            if (time > 0) {
                super.expire(key, time);
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    /**
     * 获取set缓存的长度
     *
     * @param key 键
     * @return
     */
    public Long sGetSetSize(String key) {
        try {
            return setOperations.size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    /**
     * 移除值为value的 移除指定key的缓存
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    public Long setRemove(String key, T... values) {
        try {
            return setOperations.remove(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }
}

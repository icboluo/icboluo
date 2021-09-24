package com.icboluo.common.redis;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * LIST类型相关操作
 *
 * @author icboluo
 */
@Component
@SuppressWarnings("unused")
public class RedisList<T> extends AbstractRedis<T> {
    /**
     * todo 这里注入的时候有时需要先注入 name='redisTemplate' 这里不需要为什么
     */
    @Resource
    private ListOperations<String, T> listOperations;

    @SuppressWarnings("all")
    private void example() {
        ListOperations<String, T> listOperations = redisTemplate.opsForList();
    }

    public List<T> get(String key) {
        return this.get(key, 0, -1);
    }

    /**
     * 通过索引 获取list中的值
     *
     * @param key   键
     * @param index 索引
     * @return index>=0时， 0 表头，1 第二个元素...；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     */
    public T get(String key, long index) {
        return listOperations.index(key, index);
    }

    /**
     * 获取list缓存的内容
     *
     * @param key   键
     * @param start 开始
     * @param end   结束  0 到 -1代表所有值
     * @return 获得的元素List
     */
    public List<T> get(String key, long start, long end) {
        return listOperations.range(key, start, end);
    }

    /**
     * 获取list缓存的长度
     *
     * @param key 键
     * @return size 大小
     */
    public Long size(String key) {
        return listOperations.size(key);
    }


    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return 是否新增成功
     */
    public Long add(String key, T value) {
        return listOperations.rightPush(key, value);
    }

    /**
     * 将list放入缓存 将list放入缓存并设置过期时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return 是否新增成功
     */
    public Long add(String key, T value, long time) {
        Long add = this.add(key, value);
        if (time > 0) {
            boolean expire = expire(key, time);
        }
        return add;
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return 是否新增成功
     */
    public Long addAll(String key, List<T> value) {
        return listOperations.rightPushAll(key, value);
    }

    /**
     * 将list放入缓存 将list放入缓存并设置过期时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return 是否新增成功
     */
    public Long addAll(String key, List<T> value, long time) {
        Long aLong = listOperations.rightPushAll(key, value);
        if (time > 0) {
            boolean expire = expire(key, time);
        }
        return aLong;
    }

    /**
     * 根据索引修改list中的某条数据
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     * @return 是否更新成功
     */
    public void set(String key, long index, T value) {
        listOperations.set(key, index, value);
    }


    public Long remove(String key, T value) {
        return remove(key, value, 1);
    }

    /**
     * 移除N个值为value
     *
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    public Long remove(String key, T value, long count) {
        return listOperations.remove(key, count, value);
    }
}

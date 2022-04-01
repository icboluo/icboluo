package com.icboluo.jdk.hashmap;

import lombok.Data;

import java.util.Map;
import java.util.Objects;

/**
 * 1.k执行hash算法得到hash值，hash用取余%算法得到在数组中的地址值
 * 2.头插法插入链表
 *
 * @author icboluo
 * @date 2022-03-28 23:46
 */
class MyHashMap7<K, V> {
    static final Entry<?, ?>[] EMPTY_TABLE = {};
    static final int max_capacity = 1 << 30;
    // 就是原table字段
    transient Entry<K, V>[] arr = (Entry<K, V>[]) EMPTY_TABLE;
    int threshold;
    //    快速失败机制
    transient int modCount;
    final float loadFactor;
    transient int hashSeed = 0;
    transient int size;

    public V put(K newEle, V value) {
        if (arr == EMPTY_TABLE) {
//            充气表 临界点（阈值
            inflateTable(threshold);
        }
        if (newEle == null) {
            return putForNullKey(value);
        }
        int newHash = hash(newEle);
//        计算hash值在数组中的index
        int newEleIdx = indexFor(newHash, arr.length);
        Entry<K, V> linkEle = arr[newEleIdx];
//        对数组中的链表元素进行遍历
        while (linkEle != null) {
            boolean linkEleHash = linkEle.hash == newHash;
            boolean linkEleEquals = Objects.equals(newEle, linkEle.key);
            if (!linkEleHash || !linkEleEquals) {
                linkEle = linkEle.next;
                continue;
            }
//           试着找链表中有没有新加元素
            V oldValue = linkEle.value;
            linkEle.value = value;
            return oldValue;
        }

        modCount++;
        addEntry(newHash, newEle, value, newEleIdx);
        return null;
    }

    private void inflateTable(int toSize) {
        // Find a power of 2 >= toSize
        int capacity = roundUpToPowerOf2(toSize);

        threshold = (int) Math.min(capacity * loadFactor, max_capacity + 1);
        arr = new Entry[capacity];
        initHashSeedAsNeeded(capacity);
    }

    private static int roundUpToPowerOf2(int number) {
        // assert number >= 0 : "number must be non-negative";
        if (number >= max_capacity) {
            return max_capacity;
        }
        /*
        假设，我们传入的是10，需要得到小于10的最大的2的次方数8（其实也可以直接将高位写成1，其他位遍历成0（遍历不考虑
            0000 1000   8
            0000 1010   10                  0001 ****
       >>1  0000 0101                       0000 1***
        |=  0000 1111                       0001 1***
        ...                          >>2    0000 011*
       >>>1 0000 0111                |=     0001 111*
        i-  0000 1000
         */
        int rounded = Integer.highestOneBit(number);
        /*
        原实现
        i |= (i >>  1)
        i |= (i >>  2)
        i |= (i >>  4)
        i |= (i >>  8)
        i |= (i >> 16)
        ret i-(i>>>1)  （>>>:无符号右移
         */
        if (rounded == 0) {
            return 1;
        }
//        一般情况下左移一位；如果是8，则不再移动
        if (Integer.bitCount(number) > 1) {
            return rounded << 1;
        }
        return rounded;
    }

    private V putForNullKey(V value) {
        Entry<K, V> root = arr[0];
        while (root != null) {
            if (root.key == null) {
                V oldValue = root.value;
                root.value = value;
                return oldValue;
            }
            root = root.next;
        }
        modCount++;
        addEntry(0, null, value, 0);
        return null;
    }

    void addEntry(int newHash, K key, V value, int bucketIndex) {
        if ((size >= threshold) && (null != arr[bucketIndex])) {
            resize(2 * arr.length);
            newHash = key == null ? 0 : hash(key);
            bucketIndex = indexFor(newHash, arr.length);
        }
        createEntry(newHash, key, value, bucketIndex);
    }

    void resize(int newCapacity) {
        Entry[] oldArr = arr;
        int oldCapacity = oldArr.length;
        if (oldCapacity == max_capacity) {
            threshold = Integer.MAX_VALUE;
            return;
        }

        Entry[] newArr = new Entry[newCapacity];
        transfer(newArr, initHashSeedAsNeeded(newCapacity));
        arr = newArr;
        threshold = (int) Math.min(newCapacity * loadFactor, max_capacity + 1);
    }

    void transfer(Entry[] newArr, boolean rehash) {
        int newCapacity = newArr.length;
        for (Entry<K, V> root : arr) {
            while (root != null) {
                Entry<K, V> next = root.next;
                if (rehash) {
                    root.hash = root.key == null ? 0 : hash(root.key);
                }
                /*
                1010 1010                               1011 1010
                0001 1111 31
                0000 1010 & 下标和15的时候是一致的      0001 1010 下标扩大了一倍
                 */
                int i = indexFor(root.hash, newCapacity);
                root.next = newArr[i];
                newArr[i] = root;
                root = next;
            }
        }
    }

    void createEntry(int newHash, K key, V value, int bucketIndex) {
//        数组取出来
        Entry<K, V> beforeFirst = arr[bucketIndex];
        arr[bucketIndex] = new Entry<>(newHash, key, value);
        arr[bucketIndex].next = beforeFirst;
        size++;
    }

    final boolean initHashSeedAsNeeded(int capacity) {
        boolean currentAltHashing = hashSeed != 0;
        boolean useAltHashing = sun.misc.VM.isBooted() &&
                (capacity >= MyHashMap7.Holder.ALTERNATIVE_HASHING_THRESHOLD);
        boolean switching = currentAltHashing ^ useAltHashing;
        if (switching) {
            hashSeed = useAltHashing
                    ? sun.misc.Hashing.randomHashSeed(this)
                    : 0;
        }
        return switching;
    }

    static int indexFor(int h, int length) {
        // assert Integer.bitCount(length) == 1 : "length must be a non-zero power of 2";
        /*
        0001 0000 16
        0000 1111 15
        **** **** h
        0000 **** &
        相当于%，与高位无关
         */
        return h & (length - 1);
    }

    /**
     * hash算法
     *
     * @param k key值
     * @return hash值
     */
    final int hash(Object k) {
        int h = hashSeed;
        if (0 != h && k instanceof String kStr) {
//            这个方法是一个效率不算太高的函数
            return sun.misc.Hashing.stringHash32(kStr);
        }

        h ^= k.hashCode();

        /*
        1010 1111
        0001 0101 >>>3
        1011 1111 ^
        让高位参与运算
         */

        // This function ensures that hashCodes that differ only by
        // constant multiples at each bit position have a bounded
        // number of collisions (approximately 8 at default load factor).
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    @Data
    static class Entry<K, V> implements Map.Entry<K, V> {
        final K key;
        V value;
        Entry<K, V> next;
        int hash;

        /**
         * Creates new entry.
         */
        Entry(int hash, K k, V v, Entry<K, V> next) {
            value = v;
            this.next = next;
            key = k;
            this.hash = hash;
        }

        Entry(int hash, K k, V v) {
            value = v;
            key = k;
            this.hash = hash;
        }
    }
}

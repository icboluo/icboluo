package com.icboluo.jdk.hashmap;

import lombok.AllArgsConstructor;
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
    static final int MAXIMUM_CAPACITY = 1 << 30;
    // 就是原table字段
    transient Entry<K, V>[] arr = (Entry<K, V>[]) EMPTY_TABLE;
    int threshold;
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
            linkEle.recordAccess(this);
            return oldValue;
        }

        modCount++;
        addEntry(newHash, newEle, value, newEleIdx);
        return null;
    }

    private void inflateTable(int toSize) {
        // Find a power of 2 >= toSize
        int capacity = roundUpToPowerOf2(toSize);

        threshold = (int) Math.min(capacity * loadFactor, MAXIMUM_CAPACITY + 1);
        arr = new Entry[capacity];
        initHashSeedAsNeeded(capacity);
    }

    private static int roundUpToPowerOf2(int number) {
        // assert number >= 0 : "number must be non-negative";
        int rounded = number >= MAXIMUM_CAPACITY
                ? MAXIMUM_CAPACITY
                : (rounded = Integer.highestOneBit(number)) != 0
                ? (Integer.bitCount(number) > 1) ? rounded << 1 : rounded
                : 1;

        return rounded;
    }

    private V putForNullKey(V value) {
        for (Entry<K, V> e = arr[0]; e != null; e = e.next) {
            if (e.key == null) {
                V oldValue = e.value;
                e.value = value;
                e.recordAccess(this);
                return oldValue;
            }
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
        Entry[] oldTable = arr;
        int oldCapacity = oldTable.length;
        if (oldCapacity == MAXIMUM_CAPACITY) {
            threshold = Integer.MAX_VALUE;
            return;
        }

        Entry[] newTable = new Entry[newCapacity];
        transfer(newTable, initHashSeedAsNeeded(newCapacity));
        arr = newTable;
        threshold = (int) Math.min(newCapacity * loadFactor, MAXIMUM_CAPACITY + 1);
    }

    void transfer(Entry[] newTable, boolean rehash) {
        int newCapacity = newTable.length;
        for (Entry<K, V> e : arr) {
            while (null != e) {
                Entry<K, V> next = e.next;
                if (rehash) {
                    e.hash = null == e.key ? 0 : hash(e.key);
                }
                int i = indexFor(e.hash, newCapacity);
                e.next = newTable[i];
                newTable[i] = e;
                e = next;
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

        /**
         * This method is invoked whenever the value in an entry is
         * overwritten by an invocation of put(k,v) for a key k that's already
         * in the HashMap.
         */
        void recordAccess(MyHashMap7<K, V> m) {
        }

        /**
         * This method is invoked whenever the entry is
         * removed from the table.
         */
        void recordRemoval(MyHashMap7<K, V> m) {
        }
    }
}

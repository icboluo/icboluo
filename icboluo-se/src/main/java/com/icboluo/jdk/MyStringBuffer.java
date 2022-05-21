package com.icboluo.jdk;

/**
 * @author icboluo
 * @since 2022-05-21 15:15
 */
public class MyStringBuffer {
    private StringBuffer sb;

    /**
     * A cache of the last value returned by toString. Cleared
     * whenever the StringBuffer is modified.
     */
    private transient String toStringCache;

    public synchronized MyStringBuffer append(char[] str) {
        toStringCache = null;
        super.append(str);
        return this;
    }

    public int lastIndexOf(String str) {
        // Note, synchronization achieved via invocations of other StringBuffer methods
        return lastIndexOf(str, count);
    }
}

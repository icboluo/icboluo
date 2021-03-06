package com.icboluo.se.enumdemo;

import lombok.Getter;

/**
 * @author ic菠萝
 */
class Pizza1 {
    @Getter
    private PizzaStatus status;

    public enum PizzaStatus {
        /**
         * 有序的
         */
        ORDERED,
        /**
         * 准备好
         */
        READY,
        /**
         * 递送
         */
        DELIVERED;
    }

    /**
     * 是否可以交付
     *
     * @return 如果可以返回 true
     */
    public boolean isDeliverable() {
        //用==判断就可以了，不需要用equals
        return getStatus() == PizzaStatus.READY;
    }

    // Methods that set and get the status variable.

    /**
     * 获得交付时间
     *
     * @return 时间
     */
    public int getDeliveryTimeInDays() {
        if (status.equals(PizzaStatus.ORDERED)) {
            return 5;
        }
        if (status.equals(PizzaStatus.READY)) {
            return 2;
        }
        if (status.equals(PizzaStatus.DELIVERED)) {
            return 0;
        }
        return -1;
    }
}
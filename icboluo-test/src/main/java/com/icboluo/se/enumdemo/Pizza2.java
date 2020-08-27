package com.icboluo.se.enumdemo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author icboluo
 */
 class Pizza2 {

    @Getter @Setter
    private PizzaStatus status;

    public enum PizzaStatus {
        /**
         * 有序的
         */
        ORDERED(5) {
            @Override
            public boolean isOrdered() {
                return true;
            }
        },
        /**
         * 准备好
         */
        READY(2) {
            @Override
            public boolean isReady() {
                return true;
            }
        },
        /**
         * 递送
         */
        DELIVERED(0) {
            @Override
            public boolean isDelivered() {
                return true;
            }
        };

        private final int timeToDelivery;

        public boolean isOrdered() {
            return false;
        }

        public boolean isReady() {
            return false;
        }

        public boolean isDelivered() {
            return false;
        }

        public int getTimeToDelivery() {
            return timeToDelivery;
        }

        PizzaStatus(int timeToDelivery) {
            this.timeToDelivery = timeToDelivery;
        }
    }

    public boolean isDeliverable() {
        return this.status.isReady();
    }

    public void printTimeToDeliver() {
        System.out.println("Time to delivery is " +
                this.getStatus().getTimeToDelivery());
    }

    // Methods that set and get the status variable.
}
package com.icboluo.leetcode.after_0600;

/**
 * @author icboluo
 * @since 2022-11-03 12:59
 */
class N0681_最近时刻 {
    public static void main(String[] args) {
        N0681_最近时刻 cla = new N0681_最近时刻();
        String str = "23:59";
        String s = cla.nextCloseTime(str);
        System.out.println("s = " + s);
    }

    /**
     * 求当前时间最近的下一个时间点，要求下一个时间点是由本时间点的数字构成，可以复用
     *
     * @param time 合法的时间点
     * @return
     */
    private String nextCloseTime(String time) {
        int hour = Integer.parseInt(time.substring(0, 2));
        int min = Integer.parseInt(time.substring(3));
        while (true) {
            if (++min == 60) {
                min = 0;
                hour++;
                hour %= 24;
            }
            String curTime = String.format("%02d:%02d", hour, min);
            boolean valid = true;
            for (int i = 0; i < curTime.length(); i++) {
                if (time.indexOf(curTime.charAt(i)) < 0) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                return curTime;
            }
        }
    }
}

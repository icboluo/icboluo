package com.icboluo.leetcode.建模;

import java.util.ArrayList;
import java.util.List;

/**
 * @author icboluo
 * @since 2023-01-04 20:47
 */
class N0855_考试座位 {
    private List<Integer> list;

    private int n;

    public N0855_考试座位(int n) {
        list = new ArrayList<>();
        this.n = n;
    }

    public int seat() {
        if (list.isEmpty()) {
            list.add(0);
            return 0;
        }
        int left = list.get(0);
        int right = n - list.get(list.size() - 1) - 1;
        int max = Math.max(left, right);
/*        for (int i = 0; i < list.size()-1; i++) {
            max = Math.max(max, (list.get(i + 1) - list.get(i)) / 2);
        }*/
        int idx = -1;
        for (int i = 0; i < list.size() - 1; i++) {
            int jianGe = (list.get(i + 1) - list.get(i)) / 2;
            // 起码要给idx个值，但是等于的时候后边不要覆盖前边，这里的判断需要注意点
            if (jianGe > max || (jianGe == max && idx == -1)) {
                max = jianGe;
                idx = i;
            }
        }
        // 如果左边最大
        if (left == max) {
            list.add(0, 0);
            return 0;
        }
        // 如果是中间
        if (idx != -1) {
            list.add(idx + 1, (list.get(idx + 1) + list.get(idx)) / 2);
            return list.get(idx + 1);
        }
/*        // 中间没有记录索引
        for (int i = 0; i < list.size() - 1; i++) {
            if ((list.get(i + 1) - list.get(i)) / 2 == max) {
                list.add(i + 1, (list.get(i + 1) + list.get(i)) / 2);
                return list.get(i + 1);
            }
        }*/
        // 右边
        list.add(n - 1);
        return n - 1;
    }

    public void leave(int p) {
        list.remove(Integer.valueOf(p));
    }

    public static void main(String[] args) {
        N0855_考试座位 cla = new N0855_考试座位(4);
        int seat1 = cla.seat();
        int seat2 = cla.seat();
        int seat3 = cla.seat();
        int seat4 = cla.seat();
        cla.leave(1);
        cla.leave(3);
        int seat5 = cla.seat();
        System.out.println();
    }
}

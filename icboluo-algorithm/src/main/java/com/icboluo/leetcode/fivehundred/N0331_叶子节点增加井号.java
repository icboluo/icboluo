package com.icboluo.leetcode.fivehundred;

/**
 * @author icboluo
 * @since 2022-03-28 22:22
 */
public class N0331_叶子节点增加井号 {
    public boolean isValidSerialization(String preorder) {
        String[] split = preorder.split(",");
//         元素个数
        int eleCount = 0;
        for (String str : split) {
            if (eleCount < 0) {
                return false;
            }
            eleCount--;
            if (!"#".equals(str)) {
                eleCount += 2;
            }
        }
//        可以举例子试试
        return eleCount == -1;
    }

    public static void main(String[] args) {
        N0331_叶子节点增加井号 cla = new N0331_叶子节点增加井号();
        boolean validSerialization = cla.isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#");
        System.out.println("validSerialization = " + validSerialization);
    }
}

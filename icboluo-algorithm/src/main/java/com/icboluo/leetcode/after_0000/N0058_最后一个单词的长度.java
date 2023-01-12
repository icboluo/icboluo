package com.icboluo.leetcode.after_0000;

class N0058_最后一个单词的长度 {
    public static void main(String[] args) {
        var cla = new N0058_最后一个单词的长度();
        String str = "hello world";
        int length = cla.lengthOfLastWord2(str);
        System.out.println("length = " + length);
    }

    public int lengthOfLastWord1(String str) {
        int length = 0;
        for (int i = str.length(); i > 0; i--) {
            if (str.charAt(i - 1) != ' ') {
                length++;
            } else if (length != 0) {
                return length;
            }
        }
        return length;
    }

    public int lengthOfLastWord2(String str) {
        if (str.length() == 0) {
            return 0;
        }
        int idx = 0;
        int right = str.length() - 1;
        // 去除尾部空格
        // 32就是' '这样的写法对于算法不好，会造成数字->字符之间的转换，增加理解复杂度（源码上是可以的，源码的可读性要求会低很多
        while ((right - idx >= 0) && str.charAt(right - idx) == 32) {
            idx++;
        }
        int count = 0;
        for (int i = right - idx + 1; i > 0; i--) {
            if (str.charAt(i - 1) != 32) {
                count++;
                continue;
            }
            break;
        }
        return count;
    }
}

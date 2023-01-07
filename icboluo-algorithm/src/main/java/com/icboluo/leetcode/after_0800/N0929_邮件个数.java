package com.icboluo.leetcode.after_0800;

import java.util.HashSet;
import java.util.Set;

/**
 * @author icboluo
 * @since 2022-11-22 22:02
 */
class N0929_邮件个数 {
    // 独特的邮件个数
    public int numUniqueEmails(String[] emails) {
        Set<String> set = new HashSet<>();
        for (String email : emails) {
            String[] split = email.split("@");
            String replace = split[0].replace(".", "");
            int i = replace.indexOf("+");
            // 没有减一，这里加个@区分前面和后面的字符串
            String temp = replace.substring(0, i == -1 ? replace.length() : i) + "@" + split[1];
            set.add(temp);
        }
        return set.size();
    }
}

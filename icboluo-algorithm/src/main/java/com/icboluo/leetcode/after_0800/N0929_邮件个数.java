package com.icboluo.leetcode.after_0800;

import java.util.HashSet;
import java.util.Set;

/**
 * @author icboluo
 * @since 2022-11-22 22:02
 */
class N0929_邮件个数 {
    // TODO ERROR
    public int numUniqueEmails(String[] emails) {
        Set<String> set = new HashSet<>();
        for (String email : emails) {
            String[] split = email.split("@");
            String replace = split[0].replace(".", "");
            int i = replace.indexOf("+");
            String temp = replace.substring(0, i == -1 ? replace.length() - 1 : i) + split[1];
            set.add(temp);
        }
        return set.size();
    }
}

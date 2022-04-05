package com.icboluo.othersource.guige;

/**
 * @author icboluo
 * @since 2022-03-25 19:48
 */
public class N0002_替换 {
    private String replace(String str) {
        return replace1(str);
    }

    private String replace1(String str) {
//        不要使用replace all，这个是专门为正则服务的，在这里效率较低
        return str.replace(" ", "aaa");
    }

    private String replace2(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == ' ') {
                sb.append("aaa");
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}

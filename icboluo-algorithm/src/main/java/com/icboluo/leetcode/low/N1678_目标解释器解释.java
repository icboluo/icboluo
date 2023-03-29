package com.icboluo.leetcode.low;

/**
 * @author icboluo
 * @since 2023-03-29 23:34
 */
class N1678_目标解释器解释 {
    /**
     * 将()认为是o，将(al)认为是al；其实也可以分类讨论，遇到( 判断下一个是否为 )...
     *
     * @param command
     * @return
     */
    public String interpret(String command) {
        return command.replace("(al)", "al").replace("()", "o");
    }
}

package com.icboluo.leetcode.操作系统操作问题;

/**
 * @author icboluo
 * @since 2023-02-05 13:37
 */
class N1598_爬虫日志文件 {
    public static void main(String[] args) {
        var cla = new N1598_爬虫日志文件();
        int i = cla.minOperations(new String[]{"d1/", "d2/", "../", "d21/", "./"});
        System.out.println("i = " + i);
    }

    /**
     * 移动到最初文件夹的最小操作数；按照给定顺序操作 系列问题
     *
     * @param logs
     * @return
     */
    public int minOperations(String[] logs) {
        int depth = 0;
        for (String log : logs) {
            String str = log.substring(0, log.indexOf("/"));
            if (".".equals(str)) {

            } else if ("..".equals(str)) {
                depth = Math.max(depth - 1, 0);
            } else {
                depth++;
            }
        }
        return depth;
    }
}

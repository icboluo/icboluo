package com.icboluo.leetcode.括号匹配深度;

/**
 * @author icboluo
 * @since 2023-02-05 14:06
 */
class N1111_把括号分成2部分_使深度最小 {
    /**
     * 括号嵌套深度始终为 max/2，这个已经是最小的了，无法调整，我们找出2对括号，一个放入a，一个放入b即可
     * TODO 没看懂代码啊
     *
     * @param seq
     * @return
     */
    public int[] maxDepthAfterSplit(String seq) {
        int[] res = new int[seq.length()];
        int j = 0;
        int depth = 0;
        for (int i = 0; i < seq.length(); i++) {
            char ch = seq.charAt(i);
            if (ch == '(') {
                depth++;
                res[j++] = depth % 2;
            } else {
                depth--;
                res[j++] = (depth + 1) % 2;
            }
        }
        return res;
    }
}

/**
 * 求最多能满足多少个小朋友
 * 贪心算法
 * @param g 需要的饼干大小
 * @param s 现有的饼干大小
 */
function findContentChildren(g: number[], s: number[]): number {
    g.sort()
    s.sort()
    let i = 0;
    for (let child of g) {
        // 其实这里的i判断放到外面更合理一点
        if (i < s.length && child <= s[i]) {
            i++
        }
    }
    return i;
}
// FIXME ERROR

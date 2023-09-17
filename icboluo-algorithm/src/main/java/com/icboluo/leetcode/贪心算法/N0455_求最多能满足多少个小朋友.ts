/**
 * 求最多能满足多少个小朋友
 * 贪心算法
 * @param g 需要的饼干大小
 * @param s 现有的饼干大小
 */
function findContentChildren(g: number[], s: number[]): number {
    // ts sort 必须手写
    g.sort((a, b) => a - b)
    s.sort((a, b) => a - b)
    let i = 0;
    for (let cookies of s) {
        if (i < g.length && cookies >= g[i]) {
            i++
        }
    }
    return i;
}

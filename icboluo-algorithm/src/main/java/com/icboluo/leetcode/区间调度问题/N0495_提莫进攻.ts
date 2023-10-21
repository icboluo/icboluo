// 提莫进攻 区间合并问题
function findPoisonedDuration(timeSeries: number[], duration: number): number {
    let res = 0;
    let left = timeSeries[0]
    let right = timeSeries[0] + duration
    for (let i = 1; i < timeSeries.length; i++) {
        // 如果区间不相交
        if (timeSeries[i] > right) {
            res += right - left;
            left = timeSeries[i];
        }
        right = timeSeries[i] + duration
    }
    res += right - left
    return res
}

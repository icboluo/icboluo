// 排列硬币
function arrangeCoins(n: number): number {
    let left = 0;
    let right = n;
    while (left <= right) {
        // 这么多层
        let mid = left + ((right - left) >> 1)
        let count = mid * (mid + 1) / 2
        if (count == n) {
            return mid
        } else if (count > n) {
            right = mid - 1
        } else if (count < n) {
            left = mid + 1
        }
    }
    // 求最大值，用right更合适一点
    return right
}

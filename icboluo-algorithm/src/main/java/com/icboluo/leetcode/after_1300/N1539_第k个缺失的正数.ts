// 这个系列的题都需要仔细的断点，这根本不算一个简单问题 2,3,4,7,11
function findKthPositive(arr: number[], k: number): number {
    let left = 0;
    let right = arr.length - 1
    while (left <= right) {
        let mid = left + ((right - left) >> 1)
        // 中间值差比较小，说明中间值需要变大，需要mid增大，才可以增大差值
        if (arr[mid] - mid - 1 < k) {
            left = mid + 1
        } else if (arr[mid] - mid - 1 == k) {
            left = mid + 1
        } else {
            right = mid - 1;
        }
    }
    return left + k
}
// FIXME ERROR EXP 1

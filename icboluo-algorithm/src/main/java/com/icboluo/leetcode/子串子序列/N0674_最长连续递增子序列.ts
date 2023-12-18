// 最长连续递增子序列
function findLengthOfLCIS(nums: number[]): number {
    if (!nums || nums.length == 0) {
        return 0
    }
    let max = 1;
    let temp = 1;
    for (let i = 0; i < nums.length - 1; i++) {
        if (nums[i + 1] > nums[i]) {
            temp++;
            max = Math.max(max, temp)
        } else {
            temp = 1
        }
    }
    // const eleCountMap = nums.reduce(function (pre: any, cur: any) {
    //     pre[cur] = (pre[cur] | 0) + 1
    // }, {});
    // const res = eleCountMap.values.size == [...new Set(eleCountMap.values())].length
    return max
}

console.log(findLengthOfLCIS([1, 3, 5, 4, 7]));
console.log(findLengthOfLCIS([2, 2, 2, 2, 2]));

// 01背包问题（背包是否选择）
function canPartition(nums: number[]): boolean {
    let sum = nums.reduce((a, b) => a + b)
    if (sum % 2 != 0) {
        return false;
    }
    sum /= 2;
    // dp代表i节点选择之后能否达到其和等于j
    let dp: boolean[][] = [];
};

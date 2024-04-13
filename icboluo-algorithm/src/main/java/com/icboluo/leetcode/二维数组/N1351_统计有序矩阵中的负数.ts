function countNegatives(grid: number[][]): number {
    let res = 0;
    let right = grid[0].length - 1
    for (let i = 0; i < grid.length; i++) {
        let left = 0
        // 目的是统计到从右到左的第一个非负数
        while (left <= right) {
            let mid = left + ((right - left) >> 1)
            if (grid[i][mid] < 0) {
                right = mid - 1
            } else {
                left = mid + 1
            }
        }
        res += grid[i].length - right - 1
    }
    return res
}

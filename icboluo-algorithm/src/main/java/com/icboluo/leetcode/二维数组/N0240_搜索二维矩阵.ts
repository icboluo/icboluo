function searchMatrix(matrix: number[][], target: number): boolean {
// 比较难理解的遍历形式
    // 定义行递增，列递减
    let i = 0;
    let j = matrix[0].length - 1
    while (i < matrix.length && j >= 0) {
        if (target == matrix[i][j]) {
            return true;
            // 如果当前值较大，说明需要列变小，逐渐查找
        } else if (target < matrix[i][j]) {
            j--;
            // 如果当前值较小，说明需要行变大查找
        } else if (target > matrix[i][j]) {
            i++;
        }
    }
    return false;
};

// 很简单的BFS
function orangesRotting(grid: number[][]): number {
    let queue: number[][] = []
    let oneCount = 0
    for (let i = 0; i < grid.length; i++) {
        for (let j = 0; j < grid[i].length; j++) {
            if (grid[i][j] == 2) {
                queue.push([i, j])
            } else if (grid[i][j] == 1) {
                oneCount++
            }
        }
    }
    if (oneCount == 0) {
        return 0
    }
    let count = 0
    let dirs = [[0, 1], [1, 0], [0, -1], [-1, 0]]
    while (queue.length != 0) {
        // 还是有细微的模糊差距的，比如oneCount为0的判断可不可以放到最后边一起搞了，还有为什么最终结果要减1
        count++
        let len = queue.length
        for (let i = 0; i < len; i++) {
            const pop = queue.pop();
            for (let dir of dirs) {
                let x = pop[0] + dir[0]
                let y = pop[1] + dir[1]
                if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || grid[x][y] != 1) {
                    continue
                }
                grid[x][y] = 2
                oneCount--
                queue.push([x, y])
            }
        }
    }
    return oneCount == 0 ? count - 1 : -1
}

// FIXME ERROR EXP 1

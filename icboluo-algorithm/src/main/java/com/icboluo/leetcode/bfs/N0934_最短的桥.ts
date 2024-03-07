function shortestBridge(grid: number[][]): number {
    let queue: number[][] = [];
    let findFirstBridge = false
    for (let i = 0; i < grid.length; i++) {
        for (let j = 0; j < grid[i].length; j++) {
            // 将第一个岛屿染色
            if (grid[i][j] == 1) {
                dfs(grid, i, j, queue)
                findFirstBridge = true
                break
            }
        }
        if (findFirstBridge) {
            break
        }
    }
    // 将收集到的节点BFS
    let res = 0;
    while (queue.length > 0) {
        let length = queue.length;
        for (let i = 0; i < length; i++) {
            let cur: number[] = queue.pop();
            for (let dir of dirs) {
                let dx = cur[0] + dir[0]
                let dy = cur[1] + dir[1]
                if (dx < 0 || dy < 0 || dx >= grid.length || dy >= grid[dx].length) {
                    continue
                }
                if (grid[dx][dy] == 0) {
                    queue.unshift([dx, dy]);
                    grid[dx][dy] = 2;
                } else if (grid[dx][dy] == 1) {
                    return res;
                }
            }
        }
        res++
    }
};

let dirs = [[0, 1], [0, -1], [1, 0], [-1, 0]]

function dfs(grid: number[][], i: number, j: number, queue: number[][]) {
    // 越界
    if (i < 0 || j < 0 || i >= grid.length || j >= grid[i].length || grid[i][j] == 2 || grid[i][j] == 0) {
        return
    }
    // 染色
    grid[i][j] = 2;
    queue.push([i, j])
    for (let dir of dirs) {
        dfs(grid, i + dir[0], j + dir[1], queue)
    }
}

console.log(shortestBridge([
    [0, 1],
    [1, 0]
]))

console.log(shortestBridge([
    [0, 1, 0],
    [0, 0, 0],
    [0, 0, 1]
]))

console.log(shortestBridge([
    [1, 1, 1, 1, 1],
    [1, 0, 0, 0, 1],
    [1, 0, 1, 0, 1],
    [1, 0, 0, 0, 1],
    [1, 1, 1, 1, 1]
]))

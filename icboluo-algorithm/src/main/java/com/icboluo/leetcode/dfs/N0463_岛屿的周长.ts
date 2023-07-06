// 岛屿周长 岛屿问题
// 数学解法：判断右下即可
function islandPerimeter1(grid: number[][]): number {
    let res = 0;
    for (let i = 0; i < grid.length; i++) {
        for (let j = 0; j < grid[i].length; j++) {
            if (grid[i][j] == 1) {
                // 如果是陆地，增加4个边
                res += 4;
                // 如果右边也是陆地，说明不是边界(同时对当前的右边和下一个的左边进行减少
                if (i < grid.length - 1 && grid[i + 1][j] == 1) {
                    res -= 2;
                }
                // 下边
                if (j <= grid[i].length - 1 && grid[i][j + 1] == 1) {
                    res -= 2;
                }
            }
        }
    }
    return res
}

// 普通解法 四周暴力运算
function islandPerimeter2(grid: number[][]): number {
    let res = 0;
    for (let i = 0; i < grid.length; i++) {
        for (let j = 0; j < grid[i].length; j++) {
            if (grid[i][j] == 1) {
                if (i == 0 || grid[i - 1][j] == 0) {
                    res++;
                }
                if (j == 0 || grid[i][j - 1] == 0) {
                    res++;
                }
                if (i == grid.length - 1 || grid[i + 1][j] == 0) {
                    res++;
                }
                if (j == grid[i].length - 1 || grid[i][j + 1] == 0) {
                    res++;
                }
            }
        }
    }
    return res;
}

// bfs
function islandPerimeter3(grid: number[][]): number {
    let res = 0;
    let dirs = [[0, 1], [1, 0], [0, -1], [-1, 0]];
    for (let i = 0; i < grid.length; i++) {
        for (let j = 0; j < grid[i].length; j++) {
            if (grid[i][j] == 1) {
                for (let dir of dirs) {
                    let x = i + dir[0];
                    let y = j + dir[1];
                    if (x < 0 || y < 0 || x == grid.length || y == grid[i].length || grid[x][y] == 0) {
                        res++
                    }
                }
            }
        }
    }
    return res
}

// dfs
function islandPerimeter4(grid: number[][]): number {
    let dirs = [[0, 1], [1, 0], [0, -1], [-1, 0]];

    for (let i = 0; i < grid.length; i++) {
        for (let j = 0; j < grid[i].length; j++) {
            if (grid[i][j] == 1) {
                return dfs(grid, dirs, i, j)
            }
        }
    }
    return 0;
}

function dfs(grid: number[][], dirs: number[][], i: number, j: number): number {
    // 用-1代表已经被访问过，取消了新建visited数组的空间
    grid[i][j] = -1
    let res = 0;
    for (let dir of dirs) {
        let x = i + dir[0];
        let y = j + dir[1];
        if (x < 0 || y < 0 || x == grid.length || y == grid[i].length || grid[x][y] == 0) {
            res++
        } else if (grid[x][y] == 1) {
            res += dfs(grid, dirs, x, y)
        }
    }
    return res
}

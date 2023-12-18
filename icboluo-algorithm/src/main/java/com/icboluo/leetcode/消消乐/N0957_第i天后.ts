function prisonAfterNDays(cells: number[], n: number): number[] {
    // List:List
    // @ts-ignore
    let cache = new Map()
    let temp = cells.join("")
    for (let i = 0; i < n; i++) {
        temp = cal(temp, cache)
    }
    return cells
}

function cal(cells: string, cache: any): string {
    // db 不太容易找最优子结构的时候
    if (cache.has(cells)) {
        return cache.get(cells)
    }
    let res = []
    res[0] = cells.charAt(0)
    res[7] = cells.charAt(7)
    for (let i = 1; i < 7; i++) {
        if (cells.charAt(i - 1) === cells.charAt(i + 1)) {
            res[i] = 1
        } else {
            res[i] = 0
        }
    }
    cache.set(cells, res.join(""))
    return cache.get(cells)
}

// FIXME
console.log(prisonAfterNDays([0, 1, 0, 1, 1, 0, 0, 1], 7));
console.log(prisonAfterNDays([1, 0, 0, 1, 0, 0, 1, 0], 1000000000));

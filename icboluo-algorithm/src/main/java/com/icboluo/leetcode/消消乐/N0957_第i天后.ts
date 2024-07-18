// optimize 取模备忘录，针对于循环调用的数据结构，非常好用
function prisonAfterNDays(cells: number[], n: number): number[] {
    // List:List
    // @ts-ignore
    let cache = new Map()
    while (n > 0) {
        n--;
        let temp = cells.join("")
        cache.set(temp, n);
        cells[0] = 0
        cells[7] = 0
        for (let i = 1; i < 7; i++) {
            if (temp.charAt(i - 1) === temp.charAt(i + 1)) {
                cells[i] = 1
            } else {
                cells[i] = 0
            }
        }
        if (cache.has(cells.join(""))) {
            // 计算从最高的到当前，一个完整的循环是多少
            n %= cache.get(cells.join("")) - n + 1;
        }
    }
    return cells;
}

console.log(prisonAfterNDays([0, 1, 0, 1, 1, 0, 0, 1], 7))
console.log(prisonAfterNDays([1, 0, 0, 1, 0, 0, 1, 0], 1000000000))
console.log(prisonAfterNDays([0, 1, 0, 1, 1, 0, 0, 1], 7))

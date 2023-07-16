/**相对名次 排序
 * @param {number[]} score
 * @return {string[]}
 */
function findRelativeRanks(score: number[]): string[] {
    // 0 3 2 4 1
    const numbers = score
        .map((e, i) => [e, i])
        .sort((a, b) => b[0] - a[0])
        .map(a => a[1]);
    let res = []
    for (let i = 0; i < numbers.length; i++) {
        if (i === 0) {
            res[numbers[i]] = "Gold Medal";
        } else if (i === 1) {
            res[numbers[i]] = "Silver Medal";
        } else if (i === 2) {
            res[numbers[i]] = "Bronze Medal";
        } else {
            res[numbers[i]] = (i + 1).toString()
        }
    }
    return res
};

// 三连胜；判断是否有3个连续的奇数
function threeConsecutiveOdds(arr: number[]): boolean {
    let sum = 0;
    for (let number of arr) {
        if (number % 2 != 0) {
            sum++
            if (sum == 3) {
                return true
            }
        } else {
            sum = 0
        }
    }
    return false
}

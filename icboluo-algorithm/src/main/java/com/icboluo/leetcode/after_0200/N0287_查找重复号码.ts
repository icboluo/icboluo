function findDuplicate(nums: number[]): number {
    let arr = []
    for (let num of nums) {
        if (arr.indexOf(num) == -1) {
            arr.push(num)
        } else {
            return num;
        }
    }
    return -1
}

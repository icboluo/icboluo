function numIdenticalPairs(nums: number[]): number {
    let res = 0;
    let arr: number[] = [];
    for (let i = 0; i < 101; i++) {
        arr[i] = 0;
    }
    for (let num of nums) {
        res += arr[num]++;
    }
    return res
}

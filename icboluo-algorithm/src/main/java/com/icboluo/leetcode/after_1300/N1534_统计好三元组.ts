function countGoodTriplets(arr: number[], a: number, b: number, c: number): number {
    let res = 0;
    for (let i = 0; i < arr.length - 2; i++) {
        for (let j = i + 1; j < arr.length - 1; j++) {
            for (let k = j + 1; k < arr.length; k++) {
                // 这个if可以放到上面一行进行小优化
                if (Math.abs(arr[i] - arr[j]) <= a
                    && Math.abs(arr[j] - arr[k]) <= b
                    && Math.abs(arr[i] - arr[k]) <= c) {
                    res++;
                }
            }
        }
    }
    return res
}

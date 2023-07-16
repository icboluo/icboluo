// 第三个较大的数
function thirdMax(nums: number[]): number {
    // @ts-ignore
    const numbers: number[] = [...new Set(nums)].sort((a, b) => b - a);
    return numbers.length < 3 ? numbers[0] : numbers[2];
}

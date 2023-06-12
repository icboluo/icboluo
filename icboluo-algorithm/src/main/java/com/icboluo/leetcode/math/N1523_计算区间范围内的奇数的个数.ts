function countOdds(low: number, high: number): number {
    // 尽可能的往2边扩散，大的大点，小的小点
    return Math.floor((high + 1) / 2) - Math.floor(low / 2)
};

// 2个数2进制不相同的个数
// 汉明距离
function hammingDistance(x: number, y: number): number {
    let xor = x ^ y;
    let count = 0;
    for (let i = 0; i < 32; i++) {
        count += (xor >> i) & 1;
    }
    return count;
}

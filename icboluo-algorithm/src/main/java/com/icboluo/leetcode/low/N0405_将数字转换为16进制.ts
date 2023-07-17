// 将数字转换为16进制，这个需要位操作
function toHex(num: number): string {
    // 小于0需要这样处理，也不知道为什么
    if (num < 0) {
        num += Math.pow(2, 32);
    }
    if (num == 0) {
        return "0";
    }
    let str = "0123456789abcdef";
    let res: string = "";
    while (num > 0) {
        res = str.charAt(num & 15) + res;
        num = Math.floor(num / 16);
    }
    return res;
}

console.log(toHex(26))
console.log(toHex(-1))

// 对于每个？我们仅仅尝试a、b、c3个字符即可
function modifyString(s: string): string {
    const arr = s.split("");
    for (let i = 0; i < arr.length; i++) {
        if (arr[i] != '?') {
            continue
        }
        // 这玩意写成abc是不是更好认一点
        for (let j = 0; j < 3; j++) {
            // 如果和上一个相等就算了
            if (i > 0 && arr[i - 1].charCodeAt(0) - 'a'.charCodeAt(0) == j) {
                continue
            }
            // 和下一个相等也算了
            if (i + 1 < arr.length && arr[i + 1].charCodeAt(0) - 'a'.charCodeAt(0) == j) {
                continue
            }
            // 即不和上一个相等，又不和下一个相等，我们填充当前元素即可
            arr[i] = String.fromCharCode('a'.charCodeAt(0) + j);
            break;
        }
    }
    return arr.join("");
}

console.log(modifyString("?zs"))
console.log(modifyString("ubv?w"))

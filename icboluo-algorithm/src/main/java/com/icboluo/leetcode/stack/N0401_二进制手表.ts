// 二进制手表
// @see N0401_二进制手表.java
function readBinaryWatch(turnedOn: number): string[] {
    let arr: string[] = [];
    for (let h = 0; h < 12; h++) {
        for (let m = 0; m < 60; m++) {
            let count = bitCount(h) + bitCount(m);
            if (count == turnedOn) {
                arr.push(h + ":" + ((m < 10) ? ("0" + m) : m));
            }
        }
    }
    return arr
}

function bitCount(num: number):number {
    let count = 0;
    while (num >0) {
        count += num % 2;
        num = Math.floor(num / 2);
    }
    return count;
}

console.log(readBinaryWatch(1))

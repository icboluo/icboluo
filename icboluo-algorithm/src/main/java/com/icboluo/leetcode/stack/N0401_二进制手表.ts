// 二进制手表 FIXME ERROR
function readBinaryWatch(turnedOn: number): string[] {
    let arr: string[] = [];
    for (let h = 0; h < 12; h++) {
        for (let m = 0; m < 60; m++) {
            let number = h * 60 + m;
            let count = 0;
            while (number) {
                number = number & (number - 1)
                count++
            }
            if (count == turnedOn) {
                arr.push(h + ":" + ((m < 10) ? ("0" + m) : m));
            }
        }
    }
    return arr
}

console.log(readBinaryWatch(1))

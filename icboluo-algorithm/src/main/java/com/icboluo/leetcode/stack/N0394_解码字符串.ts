function decodeString(s: string): string {
    let numbStack: number[] = []
    let resStack: string[] = []
    let i = 0
    let temp = "";
    while (i < s.length) {
        const cur = s.charAt(i);
        // 连续的数字压数栈
        if (cur >= '0' && cur <= '9') {
            let count = 0
            do {
                count = 10 * count + s.charCodeAt(i) - '0'.charCodeAt(0)
                i++
            } while (s.charAt(i) >= '0' && s.charAt(i) <= '9')
            numbStack.push(count)
        } else if (cur == '[') {
            // 左括号代表前置数据不做处理，将前置abc压入结果栈
            resStack.push(temp)
            temp = ""
            i++
        } else if (cur == ']') {
            // 右括号代表要处理，提取一组数栈和结果栈进行合并，计算结果游离，等待下一个左括号压栈或者右括号合并
            let resPop = resStack.pop();
            const numPop = numbStack.pop();
            for (let j = 0; j < numPop; j++) {
                resPop += temp
            }
            temp = resPop
            i++
        } else {
            // 连续的字符存储下来，也可以在这里一步存储到位 a/b/c/d
            temp += cur
            i++
        }
    }
    return temp
}

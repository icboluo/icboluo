function camelMatch(queries: string[], pattern: string): boolean[] {
    const res: boolean[] = []
    for (let query of queries) {
        res.push(oneEleMatch(query, pattern))
    }
    return res
}

// FIXME
function oneEleMatch(query: string, pattern: string): boolean {
    let i = 0;
    let j = 0
    let temp = true
    while (i < query.length) {
        if (query[i] >= 'a' && query[i] <= 'z') {
            i++
            continue
        }
        // 第一个字母必须是大写字母
        if (query[i++] !== pattern[j++]) {
            temp = false
            break
        }
        while (j < pattern.length && pattern[j] >= 'a' && pattern[j] <= 'z') {
            if (i === query.length) {
                break
            } else if (query[i] !== pattern[j]) {
                i++
            } else {
                i++
                j++
            }
        }
    }
    return j === pattern.length && temp
}

// FIXME
function oneEleMatch2(query: string, pattern: string): boolean {
    let i = 0;
    let j = 0
    while (i < query.length) {
        if (j < pattern.length && query[i] == pattern[j]) {
            i++
            j++
            // 剩下的都是不相等的情况
            // 小写字母可以随意添加
        } else if (query[i] >= 'a' && query[i] <= 'z') {
            i++
        } else if (query[i] >= 'A' && query[i] <= 'Z') {
            return false
        }
    }
    return true
}

console.log(camelMatch(["FooBar", "FooBarTest", "FootBall", "FrameBuffer", "ForceFeedBack"], "FB"))
console.log(camelMatch(["FooBar", "FooBarTest", "FootBall", "FrameBuffer", "ForceFeedBack"], "FoBa"))
console.log(camelMatch(["FooBar", "FooBarTest", "FootBall", "FrameBuffer", "ForceFeedBack"], "FoBaT"))

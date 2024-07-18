function camelMatch(queries: string[], pattern: string): boolean[] {
    const res: boolean[] = []
    for (let query of queries) {
        res.push(oneEleMatch(query, pattern))
    }
    return res
}

function oneEleMatch(query: string, pattern: string): boolean {
    let i = 0;
    let j = 0
    while (i < query.length && j < pattern.length) {
        if (query[i] == pattern[j]) {
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
    while (i < query.length) {
        if (query[i] >= 'a' && query[i] <= 'z') {
            i++;
        } else {
            break
        }
    }
    return i == query.length && j == pattern.length;
}

console.log(camelMatch(["FooBar", "FooBarTest", "FootBall", "FrameBuffer", "ForceFeedBack"], "FB"))
console.log(camelMatch(["FooBar", "FooBarTest", "FootBall", "FrameBuffer", "ForceFeedBack"], "FoBa"))
console.log(camelMatch(["FooBar", "FooBarTest", "FootBall", "FrameBuffer", "ForceFeedBack"], "FoBaT"))

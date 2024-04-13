// 0290 字形
function wordPattern(pattern: string, s: string): boolean {
    const strArr = s.split(" ")
    if (pattern.length != strArr.length) {
        return false;
    }
    // @ts-ignore
    let map: Map<String, String> = new Map();
    for (let i = 0; i < pattern.length; i++) {
        const cur = pattern.charAt(i);
        // 这块需要增加一个value值不重复的判断,map的取值要用get,不能[]
        if (!map.get(cur)) {
            let b = true;
            map.forEach((v, k) => {
                if (strArr[i] == v) {
                    b = false;
                }
            })
            if (!b) {
                return false;
            }
            // map的put要使用set，不能使用[]
            map.set(cur, strArr[i]);
        } else if (map.get(cur) != strArr[i]) {
            return false;
        }
    }
    return true;
}

// console.log(wordPattern('abba', 'dog cat cat dog'))
// console.log(wordPattern('abba', 'dog cat cat fish'))
// console.log(wordPattern('aaaa', 'dog cat cat dog'))
console.log(wordPattern('abba', 'dog dog dog dog'))

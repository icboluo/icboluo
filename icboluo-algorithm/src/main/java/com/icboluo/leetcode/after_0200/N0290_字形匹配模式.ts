// 字形
function wordPattern(pattern: string, s: string): boolean {
    let arr: string[] = []
    for (let i = 0; i < 26; i++) {
        arr[i] = ""
    }
    const strArr = s.split(" ");
    if (pattern.length != strArr.length) {
        return false
    }
    for (let i = 0; i < pattern.length; i++) {
        if (arr[pattern.charCodeAt(i) - 'a'.charCodeAt(0)] == "") {
            arr[pattern.charCodeAt(i) - 'a'.charCodeAt(0)] = strArr[i];
        } else if (arr[pattern.charCodeAt(i) - 'a'.charCodeAt(0)] != strArr[i]) {
            return false
        }
    }
    return true;
}
// FIXME ERROR
console.log(wordPattern('abba','dog dog dog dog'))

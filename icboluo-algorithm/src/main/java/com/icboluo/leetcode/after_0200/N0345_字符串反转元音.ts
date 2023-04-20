// 字符串的反转元音 双指针
// findFirst&findLast也可以
function reverseVowels(s: string): string {
    let l = 0;
    let r = s.length - 1;
    const arr = s.split("");
    while (l <= r) {
        while ("aeiouAEIOU".indexOf(s.charAt(l)) == -1) {
            l++
        }
        while ("aeiouAEIOU".indexOf(s.charAt(r)) == -1) {
            r--
        }
        if (l <= r) {
            let temp = arr[l];
            arr[l++] = arr[r]
            arr[r--] = temp
        }
    }
    let res = "";
    for (let string of arr) {
        res += string;
    }
    return res
}

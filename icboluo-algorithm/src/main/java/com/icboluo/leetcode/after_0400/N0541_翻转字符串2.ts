/**反转字符串
 * @param {string} s
 * @param {number} k
 * @return {string}
 */
function reverseStr(s: string, k: number): string {
    let res = s.split('')
    let i = 0;
    while (s.length - i >= 2 * k) {
        reverseArr(res, i, i + k - 1)
        i += 2 * k
    }
    if (s.length - i < k) {
        reverseArr(res, i, s.length - 1)
    } else if (s.length - i >= k && s.length - i < 2 * k) {
        reverseArr(res, i, i + k - 1)
    }
    return res.join("");
};

/**
 *反转部分数组
 * @param {string[]} arr
 * @param {number} start
 * @param {number} end
 */
function reverseArr(arr, start, end) {
    for (let i = start; i < (end + start) / 2; i++) {
        let temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp
    }
}

// FIXME ERROR
console.log(reverseStr("abcd",4))

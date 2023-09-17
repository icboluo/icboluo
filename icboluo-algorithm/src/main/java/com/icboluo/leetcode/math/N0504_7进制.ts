/**7进制 low
 * @param {number} num
 * @return {string}
 */
function convertToBase7(num: number): string {
    if (num == 0) {
        return "0"
    }
    let res = "";
    let n = Math.abs(num)
    while (n > 0) {
        res = (n % 7) + res
        n = Math.floor(n / 7)
    }
    if (num < 0) {
        return "-" + res;
    }
    return res
};

function convertToBase72(num: number): string {
    if (num < 0) {
        return "-" + convertToBase72(-num)
    }
    if (num < 7) {
        return num + ""
    }
    return convertToBase72(Math.floor(num / 7)) + num % 7
};

console.log(convertToBase7(0))

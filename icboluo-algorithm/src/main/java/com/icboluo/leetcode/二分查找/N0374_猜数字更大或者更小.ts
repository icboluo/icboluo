// 猜数字更大或者更小
var guess = function (num) {
    return 6 - num
}

function guessNumber(n: number): number {

    let left = 1;
    let right = n;
    while (left <= right) {
        let mid = left + ((right - left) >> 1);
        if (guess(mid) == 0) {
            return mid
        } else if (guess(mid) < 0) {
            right = mid - 1;
        } else if (guess(mid) > 0) {
            left = mid + 1
        }
    }
}

console.log(guessNumber(10))

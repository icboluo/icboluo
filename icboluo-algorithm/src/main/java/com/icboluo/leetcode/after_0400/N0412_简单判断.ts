// 也可以这样写，我们定义一个3|5总长的内部参数，循环递增归零，遇到=3|5的情况，记录标准，并且归零即可
function fizzBuzz(n: number): string[] {
    let arr: string[] = [];
    for (let i = 1; i <= n; i++) {
        if (i % 3 == 0 && i % 5 == 0) {
            arr[i] = "FizzBuzz"
        } else if (i % 3 == 0) {
            arr[i] = "Fizz"
        } else if (i % 5 == 5) {
            arr[i] = "Buzz";
        } else {
            arr[i] = i + "";
        }
    }
    return arr;
}
// FIXME ERROR

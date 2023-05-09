// 也可以这样写，我们定义一个3|5总长的内部参数，循环递增归零，遇到=3|5的情况，记录标准，并且归零即可
function fizzBuzz(n: number): string[] {
    let arr: string[] = [];
    for (let i = 1; i <= n; i++) {
        if (i % 3 == 0 && i % 5 == 0) {
            arr[i - 1] = "FizzBuzz"
        } else if (i % 3 == 0) {
            arr[i - 1] = "Fizz"
        } else if (i % 5 == 0) {
            arr[i - 1] = "Buzz";
        } else {
            arr[i - 1] = i + "";
        }
    }
    return arr;
}

console.log(fizzBuzz(3))
console.log(fizzBuzz(5))
console.log(fizzBuzz(15))

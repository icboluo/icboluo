// 移走最后一个棋子的玩家获胜，也可以找规律，4是一个死亡数，不管第一个人怎么选，第二个人总能凑到4上，所以，第一个人只要碰到4的倍数，永远的失败；
// 同理，只要大于4，第二个人永远取不完，变成了第一个人优势
function canWinNim(n: number): boolean {
    if (n == 0) {
        return false
    } else if (n <= 3) {
        return true
    }
    // 该方法没有问题，执行效率超时
    // return (canWinNim(n - 2) && canWinNim(n - 3) && canWinNim(n - 4))
    //     || (canWinNim(n - 3) && canWinNim(n - 4) && canWinNim(n - 5))
    //     || (canWinNim(n - 4) && canWinNim(n - 5) && canWinNim(n - 6));
    return  (n & 3) != 0
}

console.log(canWinNim(4))
console.log(canWinNim(1))
console.log(canWinNim(2))

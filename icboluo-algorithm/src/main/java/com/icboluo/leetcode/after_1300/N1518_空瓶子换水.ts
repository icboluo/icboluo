// 1518 空瓶子换水
function numWaterBottles(numBottles: number, numExchange: number): number {
    let res = numBottles;
    // 这块判断是困难的，我们不能判断当前的瓶子数是否为1，我们需要对比当前的瓶子数是否还能够参与兑换，
    // 所以，先喝完，如果能参与兑换，喝完再兑换，逻辑是这样的，res的初始值并不为0
    while (numBottles >= numExchange) {
        let duiHuanPingZi = Math.floor(numBottles / numExchange);
        res += duiHuanPingZi;
        let shengYuPingZi = numBottles - duiHuanPingZi * numExchange;
        numBottles = duiHuanPingZi + shengYuPingZi;
    }
    return res
}

console.log(numWaterBottles(9, 3))
console.log(numWaterBottles(15, 4))
console.log(numWaterBottles(15, 8))

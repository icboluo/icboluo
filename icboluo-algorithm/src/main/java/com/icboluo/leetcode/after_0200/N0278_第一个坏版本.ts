// fixme error
var solution = function (isBadVersion: any) {

    return function (n: number): number {
        let left = 1
        let right = n
        while (left <= right) {
            let mid = left + ((right - left) >> 1);
            // [mid+1,right]
            if (isBadVersion) {
                left = mid + 1;
            } else if (!isBadVersion) {
                right = mid - 1
            }
        }
        return left
    }
};

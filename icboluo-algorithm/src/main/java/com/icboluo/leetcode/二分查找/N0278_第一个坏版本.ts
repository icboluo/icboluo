var solution = function (isBadVersion: any) {

    return function (n: number): number {
        let left = 1
        let right = n
        while (left <= right) {
            let mid = left + ((right - left) >> 1);
            // [left,mid-1]
            if (isBadVersion(mid)) {
                right = mid - 1
            } else if (!isBadVersion(mid)) {
                left = mid + 1;
            }
        }
        return left
    }
};

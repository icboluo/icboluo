class Solution:
    # 计算降序数组中的负数个数 simple
    # 二分查找
    def countNegatives1(self, grid: List[List[int]]) -> int:
        count = 0
        # 读一半，只取正数
        for row in grid:
            for item in row:
                if item >= 0:
                    count += 1
                else:
                    break
        return len(grid) * len(grid[0]) - count

    def countNegatives2(self, grid: List[List[int]]) -> int:
        count = 0
        for row in grid:
            for item in reversed(row):
                if item < 0:
                    count += 1
                else:
                    break
        return count

    def countNegatives3(self, grid: List[List[int]]) -> int:
        count = 0
        for row in grid:
            left = 0
            right = len(row) - 1
            # 我们要判断什么时候 left>right,只有当left=right的时候，再走一步才会出现
            while left <= right:
                mid = int(left + (right - left) / 2)
                # [mid+1,right]
                if row[mid] == 0:
                    left = mid + 1
                #     [left,mid-1]
                elif row[mid] < 0:
                    right = mid - 1
                #     [mid+1,right]
                elif row[mid] > 0:
                    left = mid + 1
            # 此块的left是标准结果再走一步，配合len-1刚好相互抵消
            count += len(row) - left
        return count

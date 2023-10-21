# 设置不匹配,也可以用数学的加减法来处理 low
def findErrorNums(self, nums: List[int]) -> List[int]:
    res = [-1, -1]
    num_set = set(nums)
    for i in range(1, len(nums) + 1):
        if not num_set.__contains__(i):
            res[1] = i
    for item in nums:
        if num_set.__contains__(item):
            num_set.remove(item)
        else:
            res[0] = item
    return res

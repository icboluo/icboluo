class Solution:
    # 最长子序列，最大元素和最小元素差值为1
    def findLHS(self, nums: List[int]) -> int:
        eleCountMap=collections.Counter(nums)
        res=0
        for key in eleCountMap:
            # 同时包含当前元素和下一个元素，求值并最优化
            if key+1 in eleCountMap:
                res=max(res,eleCountMap[key]+eleCountMap[key+1])
        return res

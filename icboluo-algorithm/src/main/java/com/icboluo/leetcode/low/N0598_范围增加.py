class Solution:
    def maxCount(self, m: int, n: int, ops: List[List[int]]) -> int:
        rowMin=m
        colMin=n
        for dian in ops:
            rowMin=min(rowMin,dian[0])
            colMin=min(colMin,dian[1])
        return rowMin*colMin

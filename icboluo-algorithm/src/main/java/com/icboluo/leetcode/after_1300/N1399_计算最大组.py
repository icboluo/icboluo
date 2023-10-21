class Solution:
    # 计算最大组
    def countLargestGroup(self, n: int) -> int:
        kv = {}
        for i in range(1, n + 1):
            temp = 0
            while i > 0:
                temp += i % 10
                i = int(i / 10)
            kv[temp] = kv.get(temp, 0) + 1
        a = max(kv.values())
        count = 0
        for key, val in kv.items():
            if a == val:
                count += 1
        return count

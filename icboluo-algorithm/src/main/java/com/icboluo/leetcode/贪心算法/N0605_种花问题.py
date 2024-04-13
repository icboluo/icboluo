class Solution:
    # 最多呢个放多少花，花不相邻；贪心的在每个位置都放花
    def canPlaceFlowers(self, flowerbed: List[int], n: int) -> bool:
        count = 0
        for i in range(len(flowerbed)):
            if flowerbed[i] == 0:
                pre = flowerbed[i - 1] if i - 1 >= 0 else 0
                next_ele = flowerbed[i + 1] if i + 1 < len(flowerbed) else 0
                if pre == 0 and next_ele == 0:
                    flowerbed[i] = 1
                    count += 1
        return count >= n


if __name__ == '__main__':
    cla = Solution()
    # true
    print(cla.canPlaceFlowers([0, 0, 1, 0, 1], 1))
    # true
    print(cla.canPlaceFlowers([1, 0, 0, 0, 1, 0, 0], 2))

# FIXME

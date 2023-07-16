class Solution:
    def findRestaurant(self, list1: List[str], list2: List[str]) -> List[str]:
        eleIdxMap = {}
        for i in range(len(list1)):
            eleIdxMap.__setitem__(list1.__getitem__(i), i)
        minValue = 9999
        res = []
        for i in range(len(list2)):
            cur = list2.__getitem__(i)
            if list1.__contains__(cur):
                idxSum = i + eleIdxMap.get(cur)
                if idxSum == minValue:
                    res.append(cur)
                elif idxSum < minValue:
                    minValue = idxSum
                    res.clear()
                    res.append(cur)
        return res


if __name__ == '__main__':
    cla = Solution()
    print(cla.findRestaurant(["Shogun", "Tapioca Express", "Burger King", "KFC"],
                             ["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]))
    print(cla.findRestaurant(["Shogun", "Tapioca Express", "Burger King", "KFC"], ["KFC", "Shogun", "Burger King"]))
    print(cla.findRestaurant(["happy", "sad", "good"], ["sad", "happy", "good"]))

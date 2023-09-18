class Solution:
    def imageSmoother(self, img: List[List[int]]) -> List[List[int]]:
        res_arr = []
        for i in range(len(img)):
            one_arr = []
            for j in range(len(img[i])):
                neighbors = self.neighbor(i, j, img)
                one_sum = sum(img[a[0]][a[1]] for a in neighbors)
                one_arr.append(int(one_sum / len(neighbors)))
            res_arr.append(one_arr)
        return res_arr

    # 返回邻居坐标
    def neighbor(self, i: int, j: int, img: List[List[int]]) -> List[List[int]]:
        dir_arr = [[1, 0], [0, 1], [-1, 0], [0, -1], [1, 1], [-1, -1], [1, -1], [-1, 1]]
        res_arr = [[i, j]]
        for a, b in dir_arr:
            x = i + a
            y = j + b
            if 0 <= x < len(img) and 0 <= y < len(img[0]):
                res_arr.append([x, y])
        return res_arr


if __name__ == '__main__':
    cla = Solution()
    arr = [[1, 1, 1], [1, 0, 1], [1, 1, 1]]
    res = cla.imageSmoother(arr)
    print(res)
    print(cla.imageSmoother([[100, 200, 100], [200, 50, 200], [100, 200, 100]]))

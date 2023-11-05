class Solution:
    # 反转字符串中的单词
    # 方法1 双反转
    # 方法2 使用栈
    # 方法3 双指针
    def reverseWords(self, s: str) -> str:
        stack = []
        res = ''
        for i in range(len(s)):
            if s[i] != ' ' or i == len(s) - 1:
                stack.append(s[i])
            if s[i] == ' ' or i == len(s) - 1:
                for _ in range(len(stack)):
                    pop = stack.pop()
                    res += pop
                if s[i] == ' ':
                    res += ' '
        return res


if __name__ == '__main__':
    cla = Solution()
    print(cla.reverseWords("Let's take LeetCode contest"))
    print(cla.reverseWords("God Ding"))

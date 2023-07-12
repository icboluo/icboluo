class Solution:
    # 反转字符串中的单词
    # 方法1 双反转
    # 方法2 使用栈
    # 方法3 双指针
    def reverseWords(self, s: str) -> str:
        stack = []
        res = ''
        for i in range(len(s)):
            if s[i] == ' ' or i == len(s) - 1:
                for _ in range(len(stack)):
                    pop = stack.pop()
                    res += pop
                res += s[i]
            else:
                stack.append(s[i])
        return res
FIXME ERROR

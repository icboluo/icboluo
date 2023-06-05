package com.icboluo.leetcode.建模;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

/**
 * @author icboluo
 * @since 2023-06-05 23:17
 */
class N0341_扁平化嵌套列表迭代器 implements Iterator<Integer> {
    // FIXME
    // 主要这里需要一个元素可以消费的容器，list相比较而言更偏向查找，栈仅仅是操作方便，此处链表的作用完全一样
    Deque<NestedInteger> stack;

    // [[1,1],2,[3,3]]
    public N0341_扁平化嵌套列表迭代器(List<NestedInteger> nestedList) {
        stack = new ArrayDeque<>();
        for (int i = nestedList.size() - 1; i >= 0; i--) {
            stack.push(nestedList.get(i));
        }
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            return null;
        }
        return stack.pop().getInteger();
    }

    @Override
    public boolean hasNext() {
        if (!stack.isEmpty() && !stack.peek().isInteger()) {
            List<NestedInteger> list = stack.pop().getList();
            for (int i = list.size() - 1; i >= 0; i--) {
                stack.push(list.get(i));
            }
        }
        return !stack.isEmpty();
    }

    public interface NestedInteger {


        public boolean isInteger();

        public Integer getInteger();

        public List<NestedInteger> getList();
    }
}

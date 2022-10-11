package com.icboluo.leetcode.高效寻找素数;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class 素数最短距离 {
    public static void main(String[] args) {
        // 寻找所有的素数
        Set<String> set = findPrime(10000);
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            String[] arr = str.split(" ");
            int cal = cal(arr[0], arr[1], set);
            System.out.println("cal = " + cal);
        }
    }

    /**
     * BFS能简单的计算最短路径问题
     *
     * @param begin
     * @param end
     * @param set
     * @return
     */
    private static int cal(String begin, String end, Set<String> set) {
        // 队列存储当前经过'0'步之后，当前的元素可以成为那些数据
        Queue<String> queue = new LinkedList<>();
        queue.add(begin);
        // visited存储已访问过的节点，避免0023-0029-0023这种死循环
        Set<String> visited = new HashSet<>();
        visited.add(begin);
        int change = 0;
        // 循环代表BFS的每一层
        while (!queue.isEmpty()) {
            int size = queue.size();
            // BFS每一层中的每一个元素
            for (int i = 0; i < size; i++) {
                String poll = queue.poll();
                assert poll != null;
                if (poll.equals(end)) {
                    return change;
                }
                // 30年，我28岁了；回来第一件事就是让我去吃东西
                // 函数收集元素更改一个字符之后的素数
                // 不要看这里的层级比较多，这个双层for循环只是一个简简单单的0023-0029转换
                for (int j = 0; j < poll.length(); j++) {
                    // 注意，这里的右比较需要加等号，使用的是双闭区间
                    for (int k = '0'; k <= '9'; k++) {
                        // 这个arr的创建需要写到里面，经过下一行，arr已经被更改了；也就是说，每一次这个arr都代表原本的poll数据
                        char[] arr = poll.toCharArray();
                        arr[j] = (char) k;
                        String str = new String(arr);
                        if (set.contains(str) && !visited.contains(str)) {
                            queue.add(str);
                            visited.add(str);
                        }
                    }
                }
            }
            // 遍历完整整一层，遍历次数增加
            change++;
        }
        return change;
    }

    /**
     * 求所有的素数，把所有的非素数标记出来，剩下的就是素数
     *
     * @param n
     * @return
     */
    private static Set<String> findPrime(int n) {
        boolean[] notPrime = new boolean[n];
        notPrime[0] = true;
        notPrime[1] = true;
        // 这里的条件也比较重要，到log级为止
        for (int i = 2; i * i < n; i++) {
            if (!notPrime[i]) {
                // 针对于5，没有必要计算5*2和5*3了，因为2的整数倍、3的整数倍的时候已经计算过了；每次j的迭代是i的整数倍；不知道下面2种方式那种更好接受一些
                for (int j = i * i; j < n; j += i) {
                    notPrime[j] = true;
                }
/*                for (int j = i; j * i < n; j++) {
                    notPrime[j] = true;
                }*/
            }
        }
        return IntStream.range(0, n - 1).filter(i -> !notPrime[i]).mapToObj(i -> {
            String str = "000" + i;
            return str.substring(str.length() - 4);
        }).collect(Collectors.toSet());
    }
}

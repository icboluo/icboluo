package com.icboluo.leetcode.二分查找;

/**
 * @author icboluo
 * @since 2023-05-22 20:59
 */
class N0033_搜索旋转排序数组 {
    public static void main(String[] args) {
        var cla = new N0033_搜索旋转排序数组();
        System.out.println(cla.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
        System.out.println(cla.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3));
        System.out.println(cla.search(new int[]{1}, 0));
        System.out.println(cla.search(new int[]{1, 3, 5}, 3));
        System.out.println(cla.search(new int[]{1, 3}, 3));
        System.out.println(cla.search(new int[]{3, 1}, 3)); // 0
        System.out.println(cla.search(new int[]{5, 1, 3}, 5)); // 0
    }

    // 二分查找，不要嫌弃麻烦，需要找到真正的left和right，这样比较简单
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        // 先求最小值和最大值的索引
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            // 中间较大，说明最小值在右边，这里需要简单证明下
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                // 这里的-1去掉，上面的=去掉就可以了，这里如果中间值比较小，是可能是最小值的，不要去动右指针
                right = mid;
            }
        }
        right += nums.length;
        // 使用%构建二分查找
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            // 中间较大，说明最小值在右边，这里需要简单证明下
            if (nums[mid % nums.length] > target) {
                right = mid - 1;
            } else if (nums[mid % nums.length] < target) {
                left = mid + 1;
            } else {
                return mid % nums.length;
            }
        }
        return -1;
    }
}

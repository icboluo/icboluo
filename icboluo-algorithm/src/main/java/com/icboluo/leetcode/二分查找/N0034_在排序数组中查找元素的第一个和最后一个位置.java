package com.icboluo.leetcode.二分查找;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * @author icboluo
 * @since 2020-10-09 21:44
 */
class N0034_在排序数组中查找元素的第一个和最后一个位置 {
    public static void main(String[] args) {
        var cla = new N0034_在排序数组中查找元素的第一个和最后一个位置();
        int[] arr1 = {5, 7, 7, 8, 8, 10};
        var res = cla.searchRange3(arr1, 8);
        System.out.println("res = " + Arrays.toString(res));

        int[] arr2 = {5, 7, 7, 8, 8, 10};
        int idx = cla.findCount3(arr2, 8);
        System.out.println("idx = " + idx);
    }

    /**
     * 循环
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange1(int[] nums, int target) {
        int[] res = new int[2];
        Arrays.fill(res, -1);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                if (res[0] == -1) {
                    res[0] = i;
                    res[1] = i;
                } else {
                    res[1] = i;
                }
            }
        }
        return res;
    }

    /**
     * 二分查找
     * ==mid
     * <mid+1 =left
     * >mid+1 =mid
     *
     * @param arr
     * @param target
     * @return
     */
    public int[] searchRange2(int[] arr, int target) {
        int[] res = new int[2];
        Arrays.fill(res, -1);
        if (arr.length == 0) {
            return res;
        }
        // 双闭区间
        int left = 0;
        int right = arr.length - 1;
        while (left <= right && (arr[left] != target || arr[right] != target)) {
            if (left == right && arr[left] != target) {
                return res;
            }
            if (arr[left] != target) {
                left++;
                // 不加else会不会快点
            } else if (arr[right] != target) {
                right--;
            }
        }
        res[0] = left;
        res[1] = right;
        return res;
    }

    /**
     * @param arr
     * @param target
     * @return
     */
    public int[] searchRange3(int[] arr, int target) {
        int left1 = 0;
        int right1 = arr.length;
        // 左闭右开区间
        while (left1 < right1) {
            int mid = left1 + ((right1 - left1) >> 1);
            if (arr[mid] < target) {
                left1 = mid + 1;
            } else if (arr[mid] > target) {
                right1 = mid;
            } else if (arr[mid] == target) {
                right1 = mid;
            }
        }
        if (left1 > arr.length - 1 || arr[left1] != target) {
            return new int[]{-1, -1};
        }
        int left2 = 0;
        int right2 = arr.length;
        while (left2 < right2) {
            int mid = left2 + ((right2 - left2) >> 1);
            // [left,mid)
            if (arr[mid] > target) {
                right2 = mid;
            } else if (arr[mid] < target) {
                // [mid+1,right)
                left2 = mid + 1;
            } else if (arr[mid] == target) {
                // [mid+1,right)
                left2 = mid + 1;
            }
        }
        if (left2 < 1 || arr[left2 - 1] != target) {
            return new int[]{-1, -1};
        }
        return new int[]{left1, left2 - 1};
    }

    /**
     * @param arr
     * @param target
     * @return
     */
    public int[] searchRange4(int[] arr, int target) {
        int left1 = 0;
        int right1 = arr.length - 1;
        // 双闭区间
        while (left1 <= right1) {
            int mid = left1 + ((right1 - left1) >> 1);
            if (arr[mid] < target) {
                left1 = mid + 1;
            } else if (arr[mid] > target) {
                right1 = mid - 1;
            } else {
                right1 = mid - 1;
            }
        }
        if (left1 > arr.length - 1 || arr[left1] != target) {
            return new int[]{-1, -1};
        }
        int left2 = left1;
        int right2 = arr.length - 1;
        while (left2 <= right2) {
            int mid = left2 + ((right2 - left2) >> 1);
            if (arr[mid] < target) {
                left2 = mid + 1;
            } else if (arr[mid] > target) {
                right2 = mid - 1;
            } else {
                left2 = mid + 1;
            }
        }
        if (left2 < 1 || arr[left2 - 1] != target) {
            return new int[]{-1, -1};
        }
        return new int[]{left1, left2 - 1};
    }


    /**
     * OFFICE 0053 在排序数组中查找数字出现的次数
     * 挨着查找
     *
     * @param arr
     * @param target
     * @return
     */
    public int findCount1(int[] arr, int target) {
        int count = 0;
        for (int item : arr) {
            if (item == target) {
                count++;
            }
        }
        return count;
    }

    /**
     * 从两边同时找，找到数据出现的左右位置，可以减少遍历数字出现的次数
     *
     * @param arr
     * @param target
     * @return
     */
    public int findCount2(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right && (arr[left] != target || arr[right] != target)) {
            if (left == right && arr[left] != target) {
                return 0;
            }
            if (arr[left] != target) {
                left++;
                // 不加else会不会快点
            } else if (arr[right] != target) {
                right--;
            }
        }
        return right - left + 1;
    }

    /**
     * 二分查找 TODO 没看懂
     *
     * @param arr
     * @param target
     * @return
     */
    public int findCount3(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;
        int mid;
        // 先求右指针
        while (start <= end) {
            double ceil = Math.ceil((start + end) / 2);
            mid = (int) Math.ceil((start + end) / 2);
            BigDecimal se = new BigDecimal(start + end);
            if (arr[mid] <= target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        int right = start - 1;

        start = 0;
        end = arr.length - 1;
        while (start <= end) {
            mid = (int) Math.ceil((start + end) / 2);
            if (arr[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        int left = end + 1;
        return right - left + 1;
    }
}

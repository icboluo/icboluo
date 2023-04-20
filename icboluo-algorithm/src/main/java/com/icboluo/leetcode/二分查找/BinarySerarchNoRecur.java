package com.icboluo.leetcode.二分查找;

import java.util.ArrayList;
import java.util.List;

/**
 * @author icboluo
 * @since 2023-03-29 21:47
 */
class BinarySerarchNoRecur {
    public static void main(String[] args) {
        var cla = new BinarySerarchNoRecur();
        int[] arr1 = {1, 3, 8, 10, 11, 67, 100};
        System.out.println(cla.binarySearch1(arr1, 100));
        int[] arr2 = {1, 5};
        System.out.println(cla.binarySearch1(arr2, 3));
        System.out.println("-------------------------------------------");
        System.out.println(cla.binarySearch1(new int[]{1, 2, 2, 3, 4}, 2));
        System.out.println(cla.binarySearch2(new int[]{1, 2, 2, 3, 4}, 2));
        System.out.println(cla.binarySearch3(new int[]{1, 2, 2, 3, 4}, 2));
        System.out.println(cla.binarySearch4(new int[]{1, 2, 2, 3, 4}, 2));
        System.out.println(cla.binarySearch5(new int[]{1, 2, 2, 3, 4}, 2));
    }

    /**
     * 寻找左边界
     *
     * @param arr
     * @param target
     * @return
     */
    private int binarySearch1(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        // 双闭区间，什么请求下不允许继续查找 left>right
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] == target) {
                // [left,mid-1]
                right = mid - 1;
            } else if (arr[mid] > target) {
                // [left,mid-1]
                right = mid - 1;
            } else if (arr[mid] < target) {
                // [mid+1,right]
                left = mid + 1;
            }
        }
        // 索引越界；没有找到target值
        if (left == arr.length || arr[left] != target) {
            return -1;
        }
        return left;
    }

    /**
     * 寻找右边界
     *
     * @param arr
     * @param target
     * @return
     */
    private int binarySearch2(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] == target) {
                // [mid+1,right]
                left = mid + 1;
            } else if (arr[mid] > target) {
                // [left,mid-1]
                right = mid - 1;
            } else if (arr[mid] < target) {
                // [mid+1,right]
                left = mid + 1;
            }
        }
        // 索引越界；没有找到target值
        if (right < 0 || arr[right] != target) {
            return -1;
        }
        return right;
    }

    /**
     * 寻找左边界 左闭右开区间
     *
     * @param arr
     * @param target
     * @return
     */
    private int binarySearch3(int[] arr, int target) {
        int left = 0;
        int right = arr.length;
        // left=right的时候不允许执行
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] == target) {
                // [left,mid)
                right = mid;
            } else if (arr[mid] > target) {
                // [left,mid）
                right = mid;
            } else if (arr[mid] < target) {
                // [mid+1,right)
                left = mid + 1;
            }
        }
        return left;
    }

    /**
     * 寻找右边界 左闭右开区间
     *
     * @param arr
     * @param target
     * @return
     */
    private int binarySearch4(int[] arr, int target) {
        int left = 0;
        int right = arr.length;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] == target) {
                // [mid+1,right)
                left = mid + 1;
            } else if (arr[mid] > target) {
                // [left,mid)
                right = mid;
            } else if (arr[mid] < target) {
                // [mid+1,right)
                left = mid + 1;
            }
        }
        if (left - 1 < 0 || arr[left - 1] != target) {
            return -1;
        }
        return left - 1;
    }

    /**
     * 寻找区间
     *
     * @param arr
     * @param target
     * @return
     */
    private List<Integer> binarySearch5(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        List<Integer> list = new ArrayList<>();
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] == target) {
                // 这种方式的缺陷：当重复数据过多的时候，区间查找为线性的
                list.add(mid);
                int temp = mid + 1;
                while (temp < arr.length - 1 && arr[temp] == target) {
                    list.add(temp);
                    temp++;
                }
                temp = mid - 1;
                while (temp > 0 && arr[temp] == target) {
                    list.add(temp);
                    temp--;
                }
                return list;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else if (arr[mid] < target) {
                left = mid + 1;
            }
        }
        return list;
    }
}

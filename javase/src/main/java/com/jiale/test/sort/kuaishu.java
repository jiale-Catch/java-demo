package com.jiale.test.sort;

import java.util.ArrayList;
import java.util.List;

public class kuaishu {
    public static void main(String[] args) {
        int [] nums = new int[]{8,4,53,34,45,6,7,2,3,5,9,33,23,43,44};
        quickSort(nums,0,nums.length-1);
        List list = new ArrayList<>();
        for (Integer index: nums
        ) {
            list.add(index);
        }// 将基准数交换至两子数组的分界线
        System.out.println(list);
    }

    /* 快速排序 */
    static void quickSort(int[] nums, int left, int right) {
        // 子数组长度为 1 时终止递归
        if (left >= right)
            return;
        List list = new ArrayList<>();
        for (Integer index: nums
        ) {
            list.add(index);
        }// 将基准数交换至两子数组的分界线
        System.out.println(list);
        // 哨兵划分
        int pivot = partition(nums, left, right);
        System.out.println(nums[pivot]);
        // 递归左子数组、右子数组
        quickSort(nums, left, pivot - 1);
        quickSort(nums, pivot + 1, right);

    }


    /* 元素交换 */
    static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /* 哨兵划分 */
    static int partition(int[] nums, int left, int right) {
        // 以 nums[left] 为基准数
        int i = left, j = right;
        while (i < j) {
            while (i < j && nums[j] >= nums[left])
                j--;          // 从右向左找首个小于基准数的元素
            while (i < j && nums[i] <= nums[left])
                i++;          // 从左向右找首个大于基准数的元素
            swap(nums, i, j); // 交换这两个元素
        }
        swap(nums, i, left);

        return i;             // 返回基准数的索引
    }
}

package com.guofei.wu.weeknine.sort;

import java.util.Arrays;

/**
 * @author Mason
 * @author Mason
 * @version 2018/8/1
 * @since 2018/8/1
 */
public class BinarySearchArithmetic {


    public static void main(String[] args) {
//        int[] sort = {1, 30, 2, 44, 33, 78, 3, 99, 43, 78};
        int[] sort = {99, 78, 78, 44, 43, 33, 30, 3, 2, 1};
        Arrays.sort(sort);
//        System.out.println("查找结果：" + binarySearch(sort, 20));
        System.out.println("查找结果：" + recursionBinarySearch(sort, 33, 0, sort.length - 1));
    }


    /**
     * 递归二分查找
     *
     * @param sort
     * @param destNum
     * @param left
     * @param right
     * @return boolean
     * @author Mason
     * @since 2018/8/1
     */
    public static boolean recursionBinarySearch(int[] sort, int destNum, int left, int right) {

        int middle = (left + right) / 2;
        if (left >= right) {
            return false;
        }
        if (destNum > sort[middle]) {
            if (sort[0] > sort[sort.length - 1]) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
            return recursionBinarySearch(sort, destNum, left, right);
        } else if (destNum < sort[middle]) {
            if (sort[0] > sort[sort.length - 1]) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
            return recursionBinarySearch(sort, destNum, left, right);
        }
        return true;
    }

    /**
     * 二分查找
     * 查找思想：需要时排序好的数组(可以正序倒序)，以正序为例，若查找的数据比中间的大，则丢弃左边小的数据,对省下的数据进行二分查找
     * 若比中间的小，则丢弃右边的，对省下的数据进行二分查找。若相等则刚刚好.
     *
     * @param sort
     * @param destNum
     * @return boolean
     * @author Mason
     * @since 2018/8/1
     */
    public static boolean binarySearch(int[] sort, int destNum) {
        // 数组长度
        int length = sort.length;

        int left = 0;
        int right = length - 1;

        int middle = (left + right) / 2;
        // 查询终止
        while (left <= right) {
            if (destNum > sort[middle]) {
                if (sort[length - 1] > sort[0]) {
                    left = middle + 1;
                } else {
                    right = middle - 1;
                }
            } else if (destNum < sort[middle]) {
                if (sort[length - 1] > sort[0]) {
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            } else {
                return true;
            }
            middle = (left + right) / 2;
        }

        return false;
    }
}

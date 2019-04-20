package com.guofei.wu.weeknine.sort;

/**
 * 选择排序
 *
 * @author guofei.wu
 * @email guofei_wu@ucarinc.com
 * @description
 * @since 2018/7/30 10:53
 */
public class SortArithmetic {


    public static void main(String[] args) {
        int[] sort = {1, 30, 2, 44, 33, 78, 3, 99, 43, 78};
//        int[] sort = {99, 78, 78, 44, 43, 33, 30, 3, 2, 1};


//        Arrays.sort(sort);
        selectSortArithmetic(sort);
//        bubbleSortArithmetic(sort);
//        insertSortArithmetic(sort);

//        shellSortArithmetic(sort);
    }


    /**
     * shell排序：
     * 排序思想：希尔排序是把记录按下标的一定增量分组，对每组使用直接插入排序算法排序；
     * 随着增量逐渐减少，每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止.
     * 例如：初始化shell增量，N = length/2
     * 8,2，3,6,9,1,4,5，7,0
     * 若shell增量为5
     * [8,1],[2,4],[3,5],[6,7],[9,0]
     * 然后对每个分组进行直接插入排序,结果为：
     * [1,8]，[2,4],[3,5],[6,7],[0,9]
     * 合并之后为：
     * 1,2,3,4,6,0,8,5,7,9
     * 缩小shell增量为2，则分为：
     * [1,3,6,8,7],[2,4,0,5,9]
     * 每组进行直接插入排序，结果为：
     * [1,3,6,7,8],[0,2,4,5,9]
     * 合并之后为：
     * [1,0,3,2,6,4,7,5,8,9]
     * 当N为1时结束分组。
     * <p>
     * 推荐的shell增量为：
     * h = h * 3 + 1;
     *
     * @param sort
     * @author guofei.wu
     * @date 2018/7/31
     */
    public static void shellSortArithmetic(int[] sort) {
        // 增量
        int h = 1;
        int N = sort.length;
        int three = 3;
        while (N / three > h) {
            h = h * three + 1;
        }

        // h = 1时，对整个数据进行插入排序
        while (h >= 1) {
            //对分组进行插入排序
            for (int i = h; i < N; i++) {
                // 记录当前位置的数据
                int temp = sort[i];
                int j = i;
                for (; j >= h && sort[j - h] > temp; j -= h) {
                    sort[j] = sort[j - h];
                }
                //注意j的值已减去h,这里无需执行j-h操作
                sort[j] = temp;
            }
            h /= three;
        }
        print(sort, "结果：");

    }


    /**
     * 插入排序
     * 排序思想：把待排序的数组按其值的大小逐个插入到一个已经排好序的有序序列中，
     * 直到所有的纪录插入完为止，得到一个新的有序序列
     *
     * @param sort
     * @author guofei.wu
     * @date 2018/7/30
     */
    private static void insertSortArithmetic(int[] sort) {
        // 从第二个开始
        for (int i = 1; i < sort.length; i++) {
            // 将第二个元素赋值给临时变量
            int temp = sort[i];

            // 获取到上一个位置
            int j = i - 1;

            // 上一个位置存在且上一个大于当前的元素
            while (j >= 0 && sort[j] > temp) {
                // 有序组元素向后移动一个位置
                sort[j + 1] = sort[j];
                j--;
            }
            // 将待插入值插入合适的位置
            sort[j + 1] = temp;
        }
        print(sort, "结果：");
    }


    /**
     * 冒泡排序
     * 排序思想：依次两两进行比较，从小到大为例，若左边的比右边的大，则进行交换。
     * N个数字，N-1次排序，i次排序 比较N-i次
     * etc: 1, 30, 2, 44, 33, 78, 3, 99, 43, 78
     * 第一次排序：1,2,30,33,44,3,78,43,78,99
     * <p>
     * 第一次比较：1, 30, 2, 44, 33, 78, 3, 99, 43, 78
     * 第二次比较：1, 2,30, 44, 33, 78, 3, 99, 43, 78
     * 第三次比较：1, 2,30, 44, 33, 78, 3, 99, 43, 78
     * 第四次比较：1, 2,30, 33，44, 78, 3, 99, 43, 78
     * 第五次比较：1, 2,30, 33，44, 78, 3, 99, 43, 78
     * 第六次比较：1, 2,30, 33，44, 3, 78, 99, 43, 78
     * 第七次比较：1, 2,30, 33，44, 3, 78, 99, 43, 78
     * 第八次比较：1, 2,30, 33，44, 3, 78, 43, 99, 78
     * 第九次比较：1, 2,30, 33，44, 3, 78, 43, 78，99
     * <p>
     * 第二次排序：1,2,30,33,3,44,43,78,78,99
     * <p>
     * 第三次排序：1,2,30,3,33,43,44,78,78,99
     * <p>
     * 第三次排序：1,2,30,3,33,43,44,78,78,99
     * <p>
     * 第四次排序：1,2,3,30,33,43,44,78,78,99
     * <p>
     * 第五次排序：1,2,3,30,33,43,44,78,78,99
     * <p>
     * 具体可以看打印
     *
     * @param sort
     * @author guofei.wu
     * @date 2018/7/30
     */
    public static void bubbleSortArithmetic(int[] sort) {
        for (int i = 0; i < sort.length - 1; i++) {
            for (int j = 0; j < sort.length - i - 1; j++) {
                if (sort[j] > sort[j + 1]) {
                    int temp = sort[j];
                    sort[j] = sort[j + 1];
                    sort[j + 1] = temp;
                }
                print(sort, "第" + (i + 1) + "次排序,第 " + (j + 1) + "比较:");
                System.out.println("\n");
            }
            print(sort, "第" + (i + 1) + "次排序：");
            System.out.println("\n");
        }
        print(sort, "结果：");
    }


    /**
     * 选择排序
     * 排序的思想：选择第一个为最小的元素，与后面的每一个元素进行比较
     * 查找出比当前最小元素还小的最小元素，然后进行位置交换。
     *
     * @param sort
     * @author guofei.wu
     * @date 2018/7/30
     */
    public static void selectSortArithmetic(int[] sort) {

        int minIndex = 0;
        for (int i = 0; i < sort.length - 1; i++) {
            // 初始化最小元素位置
            minIndex = i;
            // 和后面的每一个元素进行比较，查找出最小的元素下标
            for (int j = i + 1; j < sort.length; j++) {
                //如果有更小的，则更改最小元素的下标
                if (sort[minIndex] > sort[j]) {
                    minIndex = j;
                }
            }
            // 最小元素下标有没有变化,若变化则交换位置
            if (minIndex != i) {
                int temp = sort[minIndex];
                sort[minIndex] = sort[i];
                sort[i] = temp;
            }
            print(sort, "第 " + (i + 1) + " 次选择：");
            System.out.println("\n");
        }
        print(sort, "结果：");
    }

    private static void print(int[] sort, String time) {
        System.out.print(time);
        for (Integer i : sort) {
            System.out.print(i + " ");
        }
    }

}

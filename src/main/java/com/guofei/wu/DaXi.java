package com.guofei.wu;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.FutureTask;
import java.util.regex.Pattern;

/**
 * @author guofei.wu
 * @version v3.0
 * @date 2019-07-02 16:31
 * @since v3.0
 */
public class DaXi {

    public static void main(String[] args) throws Exception {


        FutureTask<Integer> futureTask = new FutureTask(() -> {
            System.out.println("current thread name:" + Thread.currentThread().getName());
            Thread.sleep(10000);
            return 100;
        });

        Thread thread = new Thread(futureTask);
        thread.start();

        System.out.println("A----" + Thread.currentThread().getName());

        futureTask.isDone();
        Integer integer = futureTask.get();
        System.out.println(integer);

        System.out.println("B----" + Thread.currentThread().getName());

    }


    // 第三题
    private static int steal(int[] nums) {
        // 空返回0
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 一个数直接返回
        if (nums.length == 1) {
            return nums[0];
        }

        // 两个数直接返回最大的
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        int[] s = new int[nums.length];

        s[0] = nums[0];
        s[1] = nums[1];

        for (int i = 2; i < s.length; i++) {
            // 获取前面第两个,
            s[i] = Math.max(s[i - 2] + nums[i], s[i - 1]);
        }
        return s[s.length - 1];
    }


    // 第一题
    private static int[][] merge(int[][] intervals) {
        List<int[]> list = new ArrayList<>();
        int[] newArray = {intervals[0][0], intervals[0][1]};
        int i = 0;
        while (i < intervals.length) {
            //开始为止
            int head = newArray[1];
            int next = intervals[i][0];
            int first = newArray[0];
            // 第一个要比较的数
            int tail = intervals[i][1];
            if (next <= head) {
                newArray = new int[]{first, tail};
            } else {
                list.add(newArray);
                int[] na = {next, tail};
                newArray = na;
            }
            i++;
            if (i == intervals.length) {
                list.add(newArray);
            }
        }
        int size = list.size();
        int[][] b = new int[size][2];
        for (int j = 0; j < b.length; j++) {
            int[] ints = list.get(j);
            b[j][0] = ints[0];
            b[j][1] = ints[1];
        }
        return b;
    }


    // 第二题
    private static boolean valid(String word, String abbr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < abbr.length(); i++) {
            char c = abbr.charAt(i);
            if (i == 0) {
                sb.append(c);
                continue;
            }
            char last = sb.charAt(sb.length() - 1);

            // false 表示是数字
            boolean cb = false;
            boolean lb = false;
            if (c >= 'a' && c <= 'z') {
                cb = true;
            }
            if (last >= 'a' && last <= 'z') {
                lb = true;
            }
            // 同一类型不需要加分隔符
            if (cb == lb) {
                sb.append(c);
            } else {
                sb.append("," + c);
            }
        }

        String[] split = sb.toString().split(",");
        // 用于记录长度
        int startIndex = 0;
        for (int i = 0; i < split.length; i++) {
            String c = split[i];
            if (!isNumber(c)) {
                // 除去之前的字符
                String newString = word.substring(startIndex);
                if (newString.startsWith(c)) {
                    startIndex += c.length();
                    continue;
                }
                return false;
            } else {
                // 剩下的字符的长度是否符合要求
                if (word.length() - startIndex >= Integer.valueOf(c)) {
                    startIndex += Integer.valueOf(c);
                    continue;
                }
                return false;
            }
        }
        return startIndex == word.length();
    }

    /**
     * 判断是字母还是数字
     */
    private static boolean isNumber(String s) {
        String regex = "[0-9]+";
        Pattern compile = Pattern.compile(regex);
        return compile.matcher(s).matches();
    }


}

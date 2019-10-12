package com.guofei.wu.algorithm.sparsearray;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 稀疏数组
 *
 */
public class SparseArrayTest {


    public static void main(String[] args) throws IOException {

        int[][] chessArr = getChessArr();

        int sum = getValuesCount(chessArr);

        int[][] sparseArr = createSparseArr(chessArr, sum);


        writeSparseArr2Disk(sparseArr);


        int[][] readSparseArr = readSparseArrFromDisk();

        // 将稀疏数组转化为原始数组
        // 给原始的二维数组赋值
        int[][] newChessArr = getNewChessArr(readSparseArr);

        System.out.println("还原二维数组");
        for (int[] old : newChessArr) {
            for (int data : old) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }

    /**
     * 获取新二维数组
     *
     * @param sparseArr
     */
    private static int[][] getNewChessArr(int[][] sparseArr) {
        // 新建二维数组
        int[][] newChessArr = new int[sparseArr[0][0]][sparseArr[0][1]];

        for (int i = 1; i < sparseArr.length; i++) {
            newChessArr[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
            System.out.println();
        }
        return newChessArr;
    }

    /**
     * 从磁盘中获取二维数组
     */
    private static int[][] readSparseArrFromDisk() throws IOException {
        int[][] sparseArr;
        System.out.println("从磁盘读取稀疏数组");
        FileInputStream fis = new FileInputStream("d:\\sparse.data");
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        // 行数
        int countLine = 0;
        String line;
        List<List<Integer>> values = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            countLine++;
            String[] split = line.split(",");
            List<Integer> value = new ArrayList<>();
            value.add(Integer.valueOf(split[0]));
            value.add(Integer.valueOf(split[1]));
            value.add(Integer.valueOf(split[2]));
            values.add(value);
        }
        sparseArr = new int[countLine][3];

        // 给稀疏数组赋值
        for (int i = 0; i < sparseArr.length; i++) {
            List<Integer> value = values.get(i);
            sparseArr[i][0] = value.get(0);
            sparseArr[i][1] = value.get(1);
            sparseArr[i][2] = value.get(2);
        }
        br.close();
        fis.close();
        System.out.println("打印读取的稀疏数组：");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
        }
        return sparseArr;
    }

    /**
     * 将稀疏数组写入到磁盘
     *
     * @param sparseArr
     * @throws IOException
     */
    private static void writeSparseArr2Disk(int[][] sparseArr) throws IOException {
        System.out.println("将稀疏数组写入到磁盘");
        FileOutputStream fos = new FileOutputStream("d:\\sparse.data");
        for (int i = 0; i < sparseArr.length; i++) {
            fos.write((sparseArr[i][0] + "," + sparseArr[i][1] + "," + sparseArr[i][2] + "\n").getBytes());
        }
        fos.close();
    }

    /**
     * 创建稀疏数组
     *
     * @param chessArr
     * @param sum
     * @return
     */
    private static int[][] createSparseArr(int[][] chessArr, int sum) {
        // 创建稀疏数组
        int[][] sparseArr = new int[sum + 1][3];

        // 第一行存在多少行多少列多少个数值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        System.out.println("给稀疏数组赋值");
        // 稀疏数组的行号
        int count = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr[i][j] != 0) {
                    // 从第一行开始放数据
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr[i][j];
                }
            }
        }
        System.out.println("查看稀疏数组赋值");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
        }
        return sparseArr;
    }

    /**
     * 获取值的个数
     *
     * @param chessArr
     * @return
     */
    private static int getValuesCount(int[][] chessArr) {
        // 计算有值的个数
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr[i][j] != 0) {
                    sum++;
                }
            }
        }
        System.out.println("值的个数：" + sum);
        return sum;
    }

    /**
     * 初始化原始的二维数组
     *
     * @return
     */
    private static int[][] getChessArr() {
        // 初始化二维数组
        int[][] chessArr = new int[11][11];

        // 赋值
        chessArr[1][2] = 1;
        chessArr[2][3] = 2;

        System.out.println("原始的二维数组");
        for (int[] old : chessArr) {
            for (int data : old) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        return chessArr;
    }
}

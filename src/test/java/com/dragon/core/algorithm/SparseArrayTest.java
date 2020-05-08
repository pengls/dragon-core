package com.dragon.core.algorithm;

import com.dragon.core.algorithm.array.SparseArray;
import org.junit.Test;

/**
 * @ClassName: SparseArrayTest
 * @Description: 稀疏数组测试
 * @Author: pengl
 * @Date: 2020/5/7 13:29
 * @Version V1.0
 */
public class SparseArrayTest {
    @Test
    public void t1(){
        int[][] chessArr = new int[11][11];
        chessArr[1][2] = 1;
        chessArr[2][3] = 2;
        //输出原始的二维数组
        System.out.println("原始的二维数组：");
        print(chessArr);
        //转换为稀疏数组
        int[][] sparseArr = SparseArray.from(chessArr);
        System.out.println("压缩后的稀疏数组：");
        print(sparseArr);

        System.out.println("解压稀疏数组：");
        print(SparseArray.to(sparseArr));
    }

    private void print(int[][] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]+"\t");
            }
            System.out.println();
        }
    }
}

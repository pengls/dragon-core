package com.dragon.core.algorithm.array;

import com.dragon.core.lang.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: SparseArray
 * @Description: 稀疏数组
 * @Author: pengl
 * @Date: 2020/5/7 12:54
 * @Version V1.0
 * <p>
 * 0 0 0 0 0 0 0 0 0 0 0
 * 0 0 1 0 0 0 0 0 0 0 0
 * 0 0 0 0 2 0 0 0 0 0 0
 * 0 0 0 0 0 0 0 0 0 0 0
 * 0 0 0 0 0 0 0 0 0 0 0
 * 0 0 0 0 0 0 0 0 0 0 0
 * 0 0 0 0 0 0 0 0 0 0 0
 * 0 0 0 0 0 0 0 0 0 0 0
 * 0 0 0 0 0 0 0 0 0 0 0
 * 0 0 0 0 0 0 0 0 0 0 0
 * 0 0 0 0 0 0 0 0 0 0 0
 * <p>
 * 11 11 2
 * 1  2  1
 * 2  4  2
 */
public final class SparseArray {
    /**
     * @MethodName: from
     * @Description: 将一个普通数组转换为稀疏数组
     * @Author: pengl
     * @Date: 2020/5/7 12:55
     * @Version V1.0
     */
    public static int[][] from(int[][] arr) {
        Assert.isNotNull(arr, "arr is null");
        int sum = 0;
        List<String> locations = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                int val = arr[i][j];
                if (val != 0) {
                    locations.add(i + "," + j + "," + val);
                    sum++;
                }
            }
        }

        int[][] result = new int[sum + 1][3];
        result[0] = new int[]{arr.length, arr[0].length, sum};
        if (locations.size() > 0) {
            for (int i = 0, len = locations.size(); i < len; i++) {
                String[] ss = locations.get(i).split(",");
                result[i + 1] = new int[]{Integer.parseInt(ss[0]), Integer.parseInt(ss[1]), Integer.parseInt(ss[2])};
            }
        }

        return result;
    }

    /**
     * @MethodName: to
     * @Description: 将一个稀疏数组还原成普通数组
     * @Author: pengl
     * @Date: 2020/5/7 12:56
     * @Version V1.0
     */
    public static int[][] to(int[][] arr) {
        Assert.isNotNull(arr, "arr is null");
        int[][] result = new int[arr[0][0]][arr[0][1]];
        for (int i = 1; i < arr.length; i++) {
            int[] row = arr[i];
            result[row[0]][row[1]] = row[2];
        }
        return result;
    }
}

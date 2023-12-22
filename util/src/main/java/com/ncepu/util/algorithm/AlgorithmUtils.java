package com.ncepu.util.algorithm;

import java.util.Arrays;

/**
 * @author wengym
 * @version 1.0
 * @desc 算法工具
 * @date 2023/9/2 9:55
 */
public class AlgorithmUtils {

    /**
     * 如果data中存在cycle循环数据，则返回cycle中第一个元素在data中的位置，否则就返回-1
     * 如：cycle为['A','A','P','N','休','休']
     * 则如果data为['P','N','休','休','A','A']，则返回2（data的第三个元素）
     *
     * @param data
     *
     * @param cycle
     *
     * @author wengym
     *
     * @date 2023/10/18 14:31
     *
     * @return java.lang.Boolean
     */
    public static int getCycleIndex(String[] data, String[] cycle) {
        if (data.length < cycle.length) {
            return -1;
        }
        String[] temp = new String[cycle.length];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < cycle.length; j++) {
                temp[j] = data[(i + j) % cycle.length];
            }
            if (Arrays.equals(cycle, temp)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * data中是否存在cycle循环数据
     * 如：cycle为['A','A','P','N','休','休']
     * 则如果data为['P','N','休','休','A','A']，则返回true
     *
     * @param data
     *
     * @param cycle
     *
     * @author wengym
     *
     * @date 2023/10/18 14:31
     *
     * @return java.lang.Boolean
     */
    public static Boolean isCycle(String[] data, String[] cycle) {
        if (data.length < cycle.length) {
            return false;
        }
        String[] temp = new String[cycle.length];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < cycle.length; j++) {
                temp[j] = data[(i + j) % cycle.length];
            }
            if (Arrays.equals(cycle, temp)) {
                return true;
            }
        }
        return false;
    }
}

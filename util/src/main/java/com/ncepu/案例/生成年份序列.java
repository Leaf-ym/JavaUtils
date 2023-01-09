package com.ncepu.案例;

import com.ncepu.util.PrintUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wengym
 * @version 1.0
 * @desc TODO
 * @date 2022/2/24 15:58
 */
public class 生成年份序列 {

    public static void main(String[] args) {
        String cnaNumber = "M0511000001M";
        List<String> list = new ArrayList<>();
        for (int i = 1900; i <= 2100; i++) {
            list.add(i + "");
        }
        PrintUtils.println(list);
    }
}

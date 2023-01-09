package com.ncepu.案例;

import com.ncepu.model.GenderEnum;
import com.ncepu.util.PrintUtils;

/**
 * @author wengym
 * @version 1.0
 * @desc 测试枚举
 * @date 2022/4/11 10:14
 */
public class 认识枚举 {
    public static void main(String[] args) {
        PrintUtils.println(GenderEnum.values());
        PrintUtils.println(GenderEnum.MALE.getValue());
        PrintUtils.println(GenderEnum.MALE.getDesc());
        PrintUtils.println(GenderEnum.MALE.toString());
        PrintUtils.println(GenderEnum.FEMALE.toString());
    }
}

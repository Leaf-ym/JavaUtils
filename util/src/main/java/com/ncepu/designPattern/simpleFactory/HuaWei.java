package com.ncepu.designPattern.simpleFactory;

/**
 * @author wengym
 * @version 1.0
 * @desc 华为手机
 * @date 2023/4/28 9:25
 */
public class HuaWei implements Phone {
    /**
     * 打印手机名称
     *
     * @author wengym
     *
     * @date 2023/4/28 9:25
     *
     * @return void
     */
    @Override
    public void name() {
        System.out.println("华为手机");
    }
}

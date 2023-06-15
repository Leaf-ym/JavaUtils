package com.ncepu.designPattern.factoryMethod;

/**
 * @author wengym
 * @version 1.0
 * @desc 小米手机
 * @date 2023/4/28 9:25
 */
public class XiaoMi implements Phone {
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
        System.out.println("小米手机");
    }
}

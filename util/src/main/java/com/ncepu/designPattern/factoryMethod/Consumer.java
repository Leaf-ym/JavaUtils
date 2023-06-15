package com.ncepu.designPattern.factoryMethod;

/**
 * @author wengym
 * @version 1.0
 * @desc 消费者
 * @date 2023/4/28 9:25
 */
public class Consumer {

    public static void main(String[] args) {
        Phone p1 = new HuaWeiFactory().getPhone();
        p1.name();
        Phone p2 = new XiaoMiFactory().getPhone();
        p2.name();
    }
}

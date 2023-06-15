package com.ncepu.designPattern.simpleFactory;

/**
 * @author wengym
 * @version 1.0
 * @desc 消费者
 * @date 2023/4/28 9:25
 */
public class Consumer {

    public static void main(String[] args) {
        Phone p1 = PhoneFactory.getPhone("华为");
        Phone p2 = PhoneFactory.getPhone("小米");
        p1.name();
        p2.name();
    }
}

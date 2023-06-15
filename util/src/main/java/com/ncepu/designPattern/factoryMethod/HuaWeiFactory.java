package com.ncepu.designPattern.factoryMethod;

/**
 * @author wengym
 * @version 1.0
 * @desc 华为工厂实现类
 * @date 2023/4/28 9:25
 */
public class HuaWeiFactory implements PhoneFactory {
    /**
     * 获取手机对象
     *
     * @author wengym
     *
     * @date 2023/4/28 9:28
     *
     * @return void
     */
     @Override
     public Phone getPhone() {
         return new HuaWei();
     }
}

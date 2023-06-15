package com.ncepu.designPattern.simpleFactory;

/**
 * @author wengym
 * @version 1.0
 * @desc 手机工厂
 * @date 2023/4/28 9:25
 */
public class PhoneFactory {
    /**
     * 获取手机对象
     *
     * @param phone
     *
     * @author wengym
     *
     * @date 2023/4/28 9:28
     *
     * @return void
     */
    public static Phone getPhone(String phone) {
        switch (phone) {
            case "华为":
                return new HuaWei();
            case "小米":
                return new XiaoMi();
            default:
                return null;
        }
    }
}

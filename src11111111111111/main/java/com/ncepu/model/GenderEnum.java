package com.ncepu.model;

/**
 * @author wengym
 * @version 1.0
 * @desc 性别枚举类型
 * @date 2022/4/11 10:07
 */
public enum GenderEnum {
    MALE(1, "男"), FEMALE(2, "女");

    /**
     * 值
     */
    private Integer value;

    /**
     * 描述
     */
    private String desc;

    /***
     *
     * 构造方法
     *
     * @param value
     *
     * @param desc
     *
     * @author wengym
     *
     * @date 2022/4/11 10:19
     *
     * @return
     *
     */
    GenderEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;

    }

    public Integer getValue() {
        return this.value;
    }

    public String getDesc() {
        return this.desc;
    }

    @Override
    public String toString() {
        return this.value + "-" + this.desc;
    }
}

package com.ncepu.util.DatabaseUtils.enums;

/**
 * @author wengym
 * @version 1.0
 * @desc 数据库约束枚举类
 * @date 2022/7/14 15:54
 */
public enum Constraint {
    /**
     * 主键约束
     */
    PRIMARY_KEY("主键约束", "PRIMARY KEY"),
    /**
     * 自增长约束
     */
    AUTO_INCREMENT("自增长约束", "AUTO_INCREMENT"),
    /**
     * 非空约束
     */
    NOT_NULL("非空约束", "NOT NULL"),
    /**
     * 唯一性约束
     */
    UNIQUE("唯一性约束", "UNIQUE"),
    /**
     * 默认值约束
     */
    DEFAULT("默认值约束", "DEFAULT"),
    /**
     * 零填充约束
     */
    ZEROFILL("零填充约束", "ZEROFILL"),
    /**
     * 外键约束
     */
    FOREIGN_KEY("外键约束", "FOREIGN KEY");

    private String label;
    private String value;

    Constraint(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return value;
    }
}

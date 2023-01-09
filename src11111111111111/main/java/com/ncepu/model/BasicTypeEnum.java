package com.ncepu.model;

/**
 * @author wengym
 * @version 1.0
 * @desc 基本数据类型
 * 基本数据类型--四类八种：
 * 整数类：byte-1（-128~127）、short-2（-32768~32767）、int-4（正负21亿）、long-8）
 * 浮点类（float-4、double-8）
 * 字符类（char-2）
 * 布尔型（boolean-占一个比特位）；
 * 除此之外即为引用类数据类型。不同类型表示不同长度。
 * @date 2022/4/11 10:37
 */
public enum BasicTypeEnum {

    BYTE("byte", "Byte", "java.lang.Byte"),
    SHORT("short", "Short", "java.lang.Short"),
    INTEGER("int", "Integer", "java.lang.Integer"),
    LONG("long", "Long", "java.lang.Long"),
    FLOAT("float", "Float", "java.lang.Float"),
    DOUBLE("double", "Double", "java.lang.Double"),
    CHARACTER("char", "Character", "java.lang.Character"),
    BOOLEAN("bool", "Boolean", "java.lang.Boolean");

    /**
     * 基本数据类型名称
     */
    private String basicType;
    /**
     * 基本数据类型的对应包装类的简单类型名称
     */
    private String simpleTypeName;
    /**
     * 基本数据类型的对应包装类的完整类型名称
     */
    private String typeName;

    private BasicTypeEnum(String basicType, String simpleTypeName, String typeName) {
        this.basicType = basicType;
        this.simpleTypeName = simpleTypeName;
        this.typeName = typeName;
    }

    public String getBasicType() {
        return basicType;
    }

    public String getSimpleTypeName() {
        return this.simpleTypeName;
    }

    public String getTypeName() {
        return typeName;
    }


}

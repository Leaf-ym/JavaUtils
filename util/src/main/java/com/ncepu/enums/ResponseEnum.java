package com.ncepu.enums;

/***
 *
 * 响应码枚举类
 *
 * @author wengym
 *
 * @date 2022/4/22 10:55
 *
 */
public enum ResponseEnum {

    /**
     * 成功
     */
    SUCCESS(0, "成功"),

    /**
     * 失败
     */
    FAIL(1, "失败"),

    /**
     * 余额不足
     */
    MONEY_NOT_ENOUGH(11, "余额不足"),

    /**
     * 金额与实际金额不一致
     */
    AMOUNT_INCONSISTENT(12, "金额与实际金额不一致"),

    /**
     * 交易重复处理
     */
    DUPLICATE_TRANSACTION_PROCESSING(13, "交易重复处理"),

    /**
     * 接口无访问权限
     */
    NO_AUTH(79, "接口无访问权限"),

    /**
     * 参数校验不通过
     *
     */
    PARAM_VALIDATE_FAIL(89, "参数校验不通过"),

    /**
     * 您有未支付的订单
     *
     */
    NO_PAY_ORDER(98, "您有未支付的订单"),

    /**
     * 认证失败
     */
    AUTH_FAIL(99, "认证失败");

    private Integer code;

    private String desc;

    ResponseEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}

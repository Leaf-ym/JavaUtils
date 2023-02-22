package com.ncepu.util.ValidateUtils.validator;

public interface Validator {
    /**
     * 校验字段值是否符合规则，通过为true，不通过为false
     *
     * @param fieldValue
     *
     * @author wengym
     *
     * @date 2022/12/20 11:08
     *
     * @return java.lang.Boolean
     */
    Boolean validateField(Object fieldValue);
}

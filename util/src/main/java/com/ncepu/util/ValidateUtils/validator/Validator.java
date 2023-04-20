package com.ncepu.util.ValidateUtils.validator;

import com.ncepu.util.ValidateUtils.model.ParamModel;

/**
 * @author wengym
 * @version 1.0
 * @desc 规则校验器
 * @date 2023/4/19 13:40
 */
public interface Validator {
    /**
     * 校验字段值是否符合规则，通过为true，不通过为false
     *
     * @param model
     *
     * @author wengym
     *
     * @date 2022/12/20 11:08
     *
     * @return java.lang.Boolean
     */
    Boolean validateField(ParamModel model);
}

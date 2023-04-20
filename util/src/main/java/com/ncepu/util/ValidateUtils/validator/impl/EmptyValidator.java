package com.ncepu.util.ValidateUtils.validator.impl;

import com.ncepu.util.ValidateUtils.model.ParamModel;
import com.ncepu.util.ValidateUtils.validator.Validator;

/**
 * @author wengym
 * @version 1.0
 * @desc 空字段校验器
 * @date 2022/12/20 11:12
 */
public class EmptyValidator implements Validator {

    @Override
    public Boolean validateField(ParamModel model) {
        Object fieldValue = model.getFieldValue();
        if (fieldValue == null || "".equals(fieldValue) || "null".equals(String.valueOf(fieldValue).trim())) {
            return false;
        }
        return true;
    }
}

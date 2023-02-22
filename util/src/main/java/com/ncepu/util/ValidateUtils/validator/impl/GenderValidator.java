package com.ncepu.util.ValidateUtils.validator.impl;

import com.ncepu.util.ValidateUtils.validator.Validator;

/**
 * @author wengym
 * @version 1.0
 * @desc 性别校验器
 * @date 2022/12/27 11:05
 */
public class GenderValidator implements Validator {

    @Override
    public Boolean validateField(Object fieldValue) {
        String gender = (String)fieldValue;
        if ("1".equals(gender) || "2".equals(gender)) {
            return true;
        }
        return false;
    }
}

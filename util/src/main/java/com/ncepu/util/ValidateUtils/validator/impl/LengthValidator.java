package com.ncepu.util.ValidateUtils.validator.impl;

import com.ncepu.util.ValidateUtils.model.ParamModel;
import com.ncepu.util.ValidateUtils.validator.Validator;

/**
 * @author wengym
 * @version 1.0
 * @desc 长度校验器
 * @date 2023/04/19 11:12
 */
public class LengthValidator implements Validator {

    @Override
    public Boolean validateField(ParamModel model) {
        String fieldValue = (String)model.getFieldValue();
        if (fieldValue == null) {
            return true;
        }
        if (model.getParam() == null || "".equals(model.getParam().trim())) {
            return true;
        }
        int length = Integer.valueOf(model.getParam());
        if(fieldValue.length() > length) {
            return false;
        }
        return true;
    }
}

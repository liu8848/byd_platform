package com.platform.validators;

import com.platform.annotations.AuditorLevelValidate;
import com.platform.constant.DictKeyConstant;
import com.platform.utils.DictUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class AuditorLevelValidator implements ConstraintValidator<AuditorLevelValidate,Integer> {
    @Override
    public boolean isValid(Integer val, ConstraintValidatorContext constraintValidatorContext) {
        return DictUtil.dictMap.get(DictKeyConstant.AUDITOR_LEVEL).containsKey(Integer.toString(val));
    }
}

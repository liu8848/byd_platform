package com.platform.validators;

import com.platform.annotations.EducationValidate;
import com.platform.constant.DictKeyConstant;
import com.platform.utils.DictUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class EducationValidateValidator implements ConstraintValidator<EducationValidate,Integer> {

    @Override
    public boolean isValid(Integer val, ConstraintValidatorContext constraintValidatorContext) {
        return DictUtil.dictMap.get(DictKeyConstant.EDUCATION).containsKey(Integer.toString(val));
    }
}

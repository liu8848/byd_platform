package com.platform.validators;

import com.platform.annotations.GradeValidate;
import com.platform.constant.DictKeyConstant;
import com.platform.utils.DictUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class GradeValidator implements ConstraintValidator<GradeValidate,Integer> {
    @Override
    public boolean isValid(Integer val, ConstraintValidatorContext constraintValidatorContext) {
        return DictUtil.dictMap.get(DictKeyConstant.GRADE).containsKey(Integer.toString(val));
    }
}

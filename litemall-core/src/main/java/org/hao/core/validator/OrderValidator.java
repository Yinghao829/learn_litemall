package org.hao.core.validator;

import com.google.common.collect.Lists;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class OrderValidator implements ConstraintValidator<Order, String> {
    private List<String> valueList;
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (!valueList.contains(s.toUpperCase())) {
            return false;
        }
        return true;
    }

    @Override
    public void initialize(Order constraintAnnotation) {
        valueList = Lists.newArrayList();
        for (String val : constraintAnnotation.accepts()) {
            valueList.add(val.toUpperCase());
        }
    }
}

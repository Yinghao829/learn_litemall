package org.hao.litemall.core.validator;

import com.google.common.collect.Lists;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;


public class SortValidator implements ConstraintValidator<Sort, String> {
    private List<String> valueList;

    @Override
    public void initialize(Sort sort) {
        valueList = Lists.newArrayList();
        for (String val : sort.accepts()) {
            valueList.add(val.toLowerCase());
        }
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (!valueList.contains(s.toLowerCase())) {
            return false;
        }
        return true;
    }
}

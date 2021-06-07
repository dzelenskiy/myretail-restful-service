package com.github.dzelenskiy.myretailrestfulservice.validators;

import com.github.dzelenskiy.myretailrestfulservice.annotations.ValidCurrencyCode;
import com.github.dzelenskiy.myretailrestfulservice.enums.CurrencyCode;
import org.apache.commons.lang3.EnumUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CurrencyCodeValidator implements ConstraintValidator<ValidCurrencyCode, String> {

    @Override
    public void initialize(ValidCurrencyCode constraintAnnotation) {

        ConstraintValidator.super.initialize(constraintAnnotation);

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        return EnumUtils.isValidEnum(CurrencyCode.class, s.toUpperCase());

    }

}

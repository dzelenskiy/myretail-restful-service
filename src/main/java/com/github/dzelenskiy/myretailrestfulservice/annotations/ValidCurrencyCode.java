package com.github.dzelenskiy.myretailrestfulservice.annotations;

import com.github.dzelenskiy.myretailrestfulservice.validators.CurrencyCodeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CurrencyCodeValidator.class)
public @interface ValidCurrencyCode {

    String message() default "{com.github.dzelenskiy.myretailrestfulservice.annotations.ValidCurrencyCode.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}

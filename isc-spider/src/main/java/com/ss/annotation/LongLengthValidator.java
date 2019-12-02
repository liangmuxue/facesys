package com.ss.annotation;

import java.lang.annotation.Annotation;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class LongLengthValidator
        extends Object
        implements ConstraintValidator<LongLength, Long> {

    private long min;
    private long max;

    public void initialize(LongLength arg0) {
        this.min = arg0.min();
        this.max = arg0.max();
    }


    public boolean isValid(Long value, ConstraintValidatorContext constraintValidatorContext) {
        if (value != null && (value.toString().length() < this.min || value.toString().length() > this.max))
            return false;
        if (value == null && this.min > 0L) {
            return false;
        }
        return true;
    }

}

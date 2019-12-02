package com.ss.annotation;

import java.lang.annotation.Annotation;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class StringLengthValidator
        extends Object
        implements ConstraintValidator<StringLength, String> {

    private long min;
    private long max;

    public void initialize(StringLength arg0) {
        this.min = arg0.min();
        this.max = arg0.max();
    }


    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value != null && (value.toString().length() < this.min || value.toString().length() > this.max))
            return false;
        if (value == null && this.min > 0L) {
            return false;
        }
        return true;
    }

}

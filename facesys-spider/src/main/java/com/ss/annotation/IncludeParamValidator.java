package com.ss.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;


public class IncludeParamValidator
        extends Object
        implements ConstraintValidator<IncludeParam, Object> {

    private Class<?> include;
    private boolean notnull;

    public void initialize(IncludeParam arg0) {
        this.include = arg0.include();
        this.notnull = arg0.notnull();
    }


    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        boolean validate = false;
        try {
            if (!this.notnull && isBlank(value)) {
                validate = true;
            } else {
                String temp = String.valueOf(value);

                Object[] objs = this.include.getEnumConstants();

                for (Object obj : objs) {
                    Field key = obj.getClass().getDeclaredField("key");

                    key.setAccessible(true);
                    if (temp.equals(String.valueOf(key.get(obj)))) {
                        validate = true;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return validate;
    }


    private boolean isBlank(Object value) {
        return (value == null || StringUtils.isBlank(String.valueOf(value)));
    }

}

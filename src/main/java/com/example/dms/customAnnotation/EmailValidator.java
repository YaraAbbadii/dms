package com.example.dms.customAnnotation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<Email, String> {
    private static final String EMAIL_REGEX = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.matches(EMAIL_REGEX);
    }
}

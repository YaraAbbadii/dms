package com.example.dms.customAnnotation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ArabicLettersValidator implements ConstraintValidator<ArabicLettersOnly, String> {
    private static final String ARABIC_REGEX = "^[\\u0600-\\u06FF\\s]+$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return value.matches(ARABIC_REGEX);
    }
}

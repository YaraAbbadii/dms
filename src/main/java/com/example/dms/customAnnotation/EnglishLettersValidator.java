package com.example.dms.customAnnotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EnglishLettersValidator implements ConstraintValidator<EnglishLettersOnly, String> {
    private static final String ENGLISH_REGEX = "^[A-Za-z\\s]+$\n";


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.matches(ENGLISH_REGEX);
    }
}

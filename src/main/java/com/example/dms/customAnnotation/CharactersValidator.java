package com.example.dms.customAnnotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CharactersValidator implements ConstraintValidator<CharactersOnly, String> {

    private static final String CHARS_REGEX = "^[^\\d]*$";


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.matches(CHARS_REGEX);
    }
}

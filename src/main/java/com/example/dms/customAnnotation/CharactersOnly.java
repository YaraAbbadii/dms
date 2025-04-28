package com.example.dms.customAnnotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CharactersValidator.class)
public @interface CharactersOnly {

    String message() default "{validation.CharactersOnly.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

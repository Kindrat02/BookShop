package com.serve.mentorship.utils.validator;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Value;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CustomEmailValidator implements ConstraintValidator<Email, String> {

    @Value("${app.validation.email}")
    private boolean emailValidationEnabled;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !emailValidationEnabled || EmailValidator.getInstance().isValid(s) || s == null;
    }
}

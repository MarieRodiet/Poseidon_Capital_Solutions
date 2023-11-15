package com.nnk.springboot.utils;

import java.util.Arrays;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.passay.*;
import com.google.common.base.Joiner;

/**
 * The PasswordConstraintsValidator class implements the ConstraintValidator interface
 * to validate password constraints using the Passay library.
 * It checks if a given password meets specific criteria, such as length, uppercase,
 * digit, special character, and absence of whitespace.
 *
 * @author Marie Moore
 */
public class PasswordConstraintsValidator implements ConstraintValidator<ValidPassword, String> {

    /**
     * Initializes the validator.
     *
     * @param arg0 the annotation instance
     */
    @Override
    public void initialize(final ValidPassword arg0) {

    }

    /**
     * Validates whether the given password meets specified constraints.
     *
     * @param password the password to be validated
     * @param context  the constraint validator context
     * @return true if the password is valid, false otherwise
     */
    @Override
    public boolean isValid(final String password, final ConstraintValidatorContext context) {
        final PasswordValidator validator = new PasswordValidator(Arrays.asList(
                new LengthRule(7, 200),
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                new CharacterRule(EnglishCharacterData.Digit, 1),
                new CharacterRule(EnglishCharacterData.Special, 1),
                new WhitespaceRule()));
        final RuleResult result = validator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
        }
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(Joiner.on(",").join(validator.getMessages(result))).addConstraintViolation();
        return false;
    }
}

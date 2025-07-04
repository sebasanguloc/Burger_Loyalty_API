package org.api.burger_loyalty_api.validation.anotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.api.burger_loyalty_api.validation.validator.PasswordMatchesValidator;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchesValidator.class)
public @interface PasswordMatches {

    String message() default "Passwords do not match";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

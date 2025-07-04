package org.api.burger_loyalty_api.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.api.burger_loyalty_api.dto.UserRegisterDto;
import org.api.burger_loyalty_api.validation.anotation.PasswordMatches;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches,Object> {
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        if(obj instanceof UserRegisterDto dto){
            boolean valid = dto.getPassword().equals(dto.getRepeatPassword());
            if (!valid) {
                context.disableDefaultConstraintViolation();
                context
                        .buildConstraintViolationWithTemplate("Passwords do not match")
                        .addPropertyNode("repeatPassword")
                        .addConstraintViolation();
            }
            return valid;
        }
        return false;
    }
}

package org.api.burger_loyalty_api.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.api.burger_loyalty_api.dto.UserDto;
import org.api.burger_loyalty_api.validation.anotation.PasswordMatches;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches,Object> {
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        if(obj instanceof UserDto dto){
            return dto.getPassword().equals(dto.getRepeatPassword());
        }
        return false;
    }
}

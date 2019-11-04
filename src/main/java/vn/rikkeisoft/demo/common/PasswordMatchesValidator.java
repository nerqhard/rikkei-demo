package vn.rikkeisoft.demo.common;

import vn.rikkeisoft.demo.service.dto.PasswordResetDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        PasswordResetDTO account = (PasswordResetDTO) obj;
        return account.getPass().equals(account.getConfirmPass());
    }
}

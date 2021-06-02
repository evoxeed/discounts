package com.discount.dev.utils.validators;

import com.google.android.material.textfield.TextInputLayout;

public class PasswordValidator extends Validator {

    /**
     * Принимаем параметры. 1 объект валидатора - 1 инпут
     *
     * @param textInputLayout
     */
    public PasswordValidator(TextInputLayout textInputLayout) {
        super(textInputLayout);
    }

    @Override
    protected boolean validate(String text) {
        if (text.length() < 6) {
            textInputLayout.setError("Пароль должен иметь не меньше 6 символов");
            return false;
        }
        return true;
    }
}

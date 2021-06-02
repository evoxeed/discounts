package com.discount.dev.utils.validators;

import com.google.android.material.textfield.TextInputLayout;

public class EmailValidator extends Validator {

    /**
     * Принимаем параметры. 1 объект валидатора - 1 инпут
     *
     * @param textInputLayout
     */
    public EmailValidator(TextInputLayout textInputLayout) {
        super(textInputLayout);
    }

    @Override
    protected boolean validate(String text) {
        org.apache.commons.validator.routines.EmailValidator emailValidator = org.apache.commons.validator.routines.EmailValidator.getInstance();
        if (!emailValidator.isValid(text)) {
            textInputLayout.setError("Невалидный email");
            return false;
        }
        return true;
    }
}

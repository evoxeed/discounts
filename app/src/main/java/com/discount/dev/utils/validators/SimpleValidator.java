package com.discount.dev.utils.validators;

import com.google.android.material.textfield.TextInputLayout;

public class SimpleValidator extends Validator {

    public SimpleValidator(TextInputLayout textInputLayout) {
        super(textInputLayout);
    }

    @Override
    protected boolean validate(String text) {
        // true, т.к уже есть проверка на пустые поля
        return true;
    }
}

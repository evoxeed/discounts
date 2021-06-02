package com.discount.dev.utils.validators;

import android.webkit.URLUtil;

import com.google.android.material.textfield.TextInputLayout;

public class URLValidator extends Validator {
    /**
     * Принимаем параметры. 1 объект валидатора - 1 инпут
     *
     * @param textInputLayout
     */
    public URLValidator(TextInputLayout textInputLayout) {
        super(textInputLayout);
    }

    @Override
    protected boolean validate(String text) {
        return URLUtil.isValidUrl(text);
    }
}

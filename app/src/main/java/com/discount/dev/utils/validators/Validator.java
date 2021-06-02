package com.discount.dev.utils.validators;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

import com.google.android.material.textfield.TextInputLayout;

/**
 * Кастомный валидатор для полей
 */
public abstract class Validator implements TextWatcher {

    protected final TextInputLayout textInputLayout;

    /**
     * Принимаем параметры. 1 объект валидатора - 1 инпут
     *
     * @param textInputLayout
     */
    public Validator(TextInputLayout textInputLayout) {
        this.textInputLayout = textInputLayout;
    }

    /**
     * Проверяем в целом на валидность поле
     *
     * @param text
     * @return
     */
    protected boolean isValid(String text) {
        if (!validateEmpty(text)) {
            textInputLayout.setError("Это обязательное поле");
            return false;
        }
        return true;
    }

    protected abstract boolean validate(String text);

    /**
     * Проверяем на пустоту
     *
     * @param text
     * @return
     */
    private boolean validateEmpty(String text) {
        return !TextUtils.isEmpty(text);
    }

    // Дефолтные обработчики от TextWatcher
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        boolean isValid = !isValid(s.toString()) || !validate(s.toString());
        textInputLayout.setErrorEnabled(isValid);
    }
}
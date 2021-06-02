package com.discount.dev.utils;

import android.app.Activity;
import android.content.res.Configuration;

import com.discount.dev.R;

public class ThemeUtil {

    /**
     * Тема устройства
     *
     * @return int
     */
    public static final int getDeviceTheme(Activity activity) {
        int theme = R.style.AppTheme_Light;
        final int currentNightMode = activity.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (currentNightMode) {
            case Configuration.UI_MODE_NIGHT_NO:
                theme = R.style.AppTheme_Light;
                break;
            case Configuration.UI_MODE_NIGHT_YES:
                theme = R.style.AppTheme_Dark;
                break;
        }

        return theme;
    }
}

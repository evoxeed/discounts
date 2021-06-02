package com.discount.dev.utils;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.discount.dev.App;
import com.discount.dev.R;

public class PrefUtil {
    private static final SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(App.getInstance());

    public static int getTheme() {
        return mPrefs.getInt("theme", R.style.AppTheme_Light);
    }

    public static void setTheme(int theme) {
        mPrefs.edit().putInt("theme", theme).apply();
    }

    /**
     * Получать тему из устройства
     *
     * @return bool
     */
    public static boolean isThemeDevice() {
        return mPrefs.getBoolean("is_device_theme", true);
    }

    /**
     * Сохранение настройки "Тема устройства"
     *
     * @param state
     */
    public static void setThemeDevice(boolean state) {
        mPrefs.edit().putBoolean("is_device_theme", state).apply();
    }

    public static void saveAccessToken(String token) {
        mPrefs.edit().putString("access_token", token).apply();
        saveLastUpdateAccessToken();
    }

    public static String getAccessToken() {
       return mPrefs.getString("access_token", "");
    }

    public static void saveExpiresIn(int expired) {
        mPrefs.edit().putInt("expires_in", expired).apply();
    }

    public static int getExpiresIn() {
        return mPrefs.getInt("expires_in", 0);
    }

    public static void saveLastUpdateAccessToken() {
        mPrefs.edit().putLong("last_update_access_token", System.currentTimeMillis()).apply();
    }

    public static long getLastUpdateAccessToken() {
        return mPrefs.getLong("last_update_access_token", 0);
    }
}

package com.discount.dev.Services;

import android.util.Log;
import android.widget.Toast;

import com.discount.dev.App;
import com.discount.dev.Retrofit.Models.Authorization;
import com.discount.dev.Retrofit.Models.RefreshToken;
import com.discount.dev.Retrofit.NetworkService;
import com.discount.dev.utils.PrefUtil;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginService {

    public static boolean checkUpdateAccessToken() {
        long lastUpdate = PrefUtil.getLastUpdateAccessToken();
        int expiresIn = PrefUtil.getExpiresIn();
        if (lastUpdate != 0 && expiresIn != 0) {
            Timestamp timestamp = new Timestamp(lastUpdate);
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(timestamp.getTime());
            cal.add(Calendar.SECOND, expiresIn);
            Timestamp later = new Timestamp(cal.getTime().getTime());

            Log.e("Next refresh in", later.toString());

            return later.getTime() < System.currentTimeMillis();
        }

        return false;
    }

    public static void refreshToken() {
        Call<RefreshToken> call = NetworkService.getService().refreshToken("Bearer " + PrefUtil.getAccessToken());
        call.enqueue(new Callback<RefreshToken>() {
            @Override
            public void onResponse(Call<RefreshToken> call, Response<RefreshToken> response) {
                if (response.isSuccessful()) {
                    RefreshToken refreshToken = response.body();
                    handleLoginResponse(refreshToken);
                    return;
                }
                handleErrorResponse(response.errorBody());
            }
            @Override
            public void onFailure(Call<RefreshToken> call, Throwable t) {
            }
        });
    }

    public static void handleLoginResponse(Authorization authorization) {
        // Save token
        PrefUtil.saveAccessToken(authorization.getAccessToken());
        // Save expires in
        PrefUtil.saveExpiresIn(authorization.getExpiresIn()); // seconds
    }

    public static void handleErrorResponse(ResponseBody response) {
        try {
            Log.e("ERROR API", response.string());
            Toast.makeText(App.getInstance(), "Неверный логин или пароль", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void fetchLogin(String login, String password, Callback<Authorization> callback) {
        Call<Authorization> call = NetworkService.getService().auth(login, password);
        call.enqueue(callback);
    }
}

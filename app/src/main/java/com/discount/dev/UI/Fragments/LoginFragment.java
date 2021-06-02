package com.discount.dev.UI.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.discount.dev.App;
import com.discount.dev.R;
import com.discount.dev.Retrofit.Models.Authorization;
import com.discount.dev.utils.PrefUtil;
import com.discount.dev.utils.validators.EmailValidator;
import com.discount.dev.utils.validators.PasswordValidator;
import com.google.android.material.textfield.TextInputLayout;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.discount.dev.Services.LoginService.fetchLogin;
import static com.discount.dev.Services.LoginService.handleErrorResponse;
import static com.discount.dev.Services.LoginService.handleLoginResponse;

public class LoginFragment extends Fragment {

    private ProgressDialog mProgressDialog;
    NavController navController;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        // todo Fragment
        EditText login = rootView.findViewById(R.id.login_input);
        TextInputLayout loginWrapper = rootView.findViewById(R.id.login_wrapper);
        login.addTextChangedListener(new EmailValidator(loginWrapper));

        EditText password = rootView.findViewById(R.id.password_input);
        TextInputLayout passwordWrapper = rootView.findViewById(R.id.password_wrapper);
        password.addTextChangedListener(new PasswordValidator(passwordWrapper));

        rootView.findViewById(R.id.authBtn).setOnClickListener(view -> {
            mProgressDialog = new ProgressDialog(rootView.getContext());
            mProgressDialog.setTitle("Авторизация");
            mProgressDialog.setMessage("Вход...");
            mProgressDialog.show();

            // todo вынести в классы запросов, написать ядро для запросов
            fetchLogin(login.getText().toString(), password.getText().toString(), new Callback<Authorization>() {
                @Override
                public void onResponse(@NotNull Call<Authorization> call, @NotNull Response<Authorization> response) {
                    mProgressDialog.dismiss();
                    // example@mail.ru (123456)
                    if (response.isSuccessful()) {
                        Authorization authorization = response.body();
                        handleLoginResponse(authorization);
                        Toast.makeText(App.getInstance(), "Успешно вошли!", Toast.LENGTH_SHORT).show();
                        navController.navigate(R.id.action_loginFragment_to_mainFragment);
                        return;
                    }

                    handleErrorResponse(response.errorBody());
                    loginWrapper.setError("Неверный логин или пароль");
                    loginWrapper.setErrorEnabled(true);
                }

                @Override
                public void onFailure(@NotNull Call<Authorization> call, @NotNull Throwable t) {
                    mProgressDialog.dismiss();
                }
            });
        });

        return rootView;
    }
}
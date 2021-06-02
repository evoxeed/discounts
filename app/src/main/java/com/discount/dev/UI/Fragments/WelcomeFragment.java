package com.discount.dev.UI.Fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.discount.dev.App;
import com.discount.dev.R;
import com.discount.dev.utils.PrefUtil;

public class WelcomeFragment extends Fragment {
    Button login;
    Button registry;
    NavController navController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        View rootView = inflater.inflate(R.layout.fragment_welcome, container, false);
        if (!TextUtils.isEmpty(PrefUtil.getAccessToken())) {
            // Костыль с навигацией по 2 фрагментам
            navController.navigate(R.id.action_welcomeFragment_to_loginFragment);
            navController.navigate(R.id.action_loginFragment_to_mainFragment);
            return rootView;
        }

        login = rootView.findViewById(R.id.login);
        login.setOnClickListener(v -> navController.navigate(R.id.action_welcomeFragment_to_loginFragment));

        registry = rootView.findViewById(R.id.registry);
        registry.setOnClickListener(v -> navController.navigate(R.id.action_welcomeFragment_to_registryFragment));

        return rootView;
    }
}
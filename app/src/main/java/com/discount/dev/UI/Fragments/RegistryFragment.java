package com.discount.dev.UI.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.discount.dev.R;

public class RegistryFragment extends Fragment {
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
        View rootView = inflater.inflate(R.layout.fragment_registry, container, false);
        registry = rootView.findViewById(R.id.registryBtn);
        registry.setOnClickListener(v -> navController.navigate(R.id.action_registryFragment_to_mainFragment));
        return rootView;
    }
}
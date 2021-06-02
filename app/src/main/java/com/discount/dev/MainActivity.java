package com.discount.dev;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.discount.dev.utils.PrefUtil;
import com.discount.dev.utils.ThemeUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static com.discount.dev.Services.LoginService.checkUpdateAccessToken;
import static com.discount.dev.Services.LoginService.refreshToken;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (PrefUtil.isThemeDevice()) {
            int theme = ThemeUtil.getDeviceTheme(this);
            setTheme(theme);
        } else {
            setTheme(PrefUtil.getTheme());
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (checkUpdateAccessToken()) {
            Log.e("Refresh", "refresh access token...");
            refreshToken();
        } else {
            Log.e("Refresh", "don't refresh access token...");
        }

        BottomNavigationView navView = findViewById(R.id.bottom_navigation);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.main_graph_xml).build();
        View main = findViewById(R.id.home);
        View search = findViewById(R.id.search);
        View profile = findViewById(R.id.profile);
        navView.setItemIconTintList(null);

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (destination.getId() == R.id.welcomeFragment || destination.getId() == R.id.registryFragment || destination.getId() == R.id.loginFragment) {
                navView.setVisibility(View.GONE);
            } else {
                navView.setVisibility(View.VISIBLE);
            }
        });

        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        return true;

                    case R.id.search:
                        return true;

                    case R.id.profile:
                        return true;
                }
                return false;
            }

        });

        main.setOnClickListener(view -> {
            navController.navigate(R.id.action_global_mainFragment);
            navView.setSelectedItemId(R.id.home);
        });

        search.setOnClickListener(view -> {
            navController.navigate(R.id.action_global_searchFragment2);
            navView.setSelectedItemId(R.id.search);

        });

        profile.setOnClickListener(view -> {
            navController.navigate(R.id.action_global_profileFragment);
            navView.setSelectedItemId(R.id.profile);
        });


        getSupportActionBar().hide();
    }


}

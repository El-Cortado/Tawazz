package com.example.tawazz.utils.gui;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.NavDirections;

import com.example.tawazz.utils.Supplier;

public class NavigationOnClickListener implements View.OnClickListener {
    private NavController mNavController;
    private Supplier<NavDirections> mNavDirectionSupplier;

    public NavigationOnClickListener(NavController navController, Supplier<NavDirections> mNavDirectionSupplier) {
        this.mNavController = navController;
        this.mNavDirectionSupplier = mNavDirectionSupplier;
    }

    public NavigationOnClickListener(NavController navController, final NavDirections navDirections) {
        this.mNavController = navController;
        this.mNavDirectionSupplier = new Supplier<NavDirections>() {
            @Override
            public NavDirections supply() {
                return navDirections;
            }
        };
    }

    @Override
    public void onClick(View v) {
        mNavController.navigate(mNavDirectionSupplier.supply());
    }
}

package com.example.tawazz.utils.gui;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.NavDirections;

public class NavigationOnClickListener implements View.OnClickListener {
    private NavController mNavController;
    private NavDirections mNavDirections;

    public NavigationOnClickListener(NavController navController, NavDirections navDirections) {
        this.mNavController = navController;
        this.mNavDirections = navDirections;
    }

    @Override
    public void onClick(View v) {
        mNavController.navigate(mNavDirections);
    }
}

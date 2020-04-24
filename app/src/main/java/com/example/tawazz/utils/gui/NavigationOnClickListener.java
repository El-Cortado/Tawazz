package com.example.tawazz.utils.gui;

import android.view.View;

import androidx.navigation.NavController;

public class NavigationOnClickListener implements View.OnClickListener {
    private NavController mNavController;
    private int actionId;

    public NavigationOnClickListener(NavController navController, int actionId) {
        this.mNavController = navController;
        this.actionId = actionId;
    }

    @Override
    public void onClick(View v) {
        mNavController.navigate(actionId);
    }
}

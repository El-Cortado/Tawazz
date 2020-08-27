package com.example.tawazz.utils.gui;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.NavDirections;

import com.example.tawazz.utils.supplier.Supplier;
import com.example.tawazz.utils.supplier.ThrowingSupplier;
import com.example.tawazz.utils.exceptions.SupplyingException;
import com.google.android.material.snackbar.Snackbar;

public class NavigationOnClickListener implements View.OnClickListener {
    private NavController mNavController;
    private ThrowingSupplier<NavDirections> mNavDirectionSupplier;

    public NavigationOnClickListener(NavController navController, ThrowingSupplier<NavDirections> mNavDirectionSupplier) {
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
        try {
            mNavController.navigate(mNavDirectionSupplier.supply());
        } catch (SupplyingException e) {
            Snackbar mySnackbar = Snackbar.make(v, e.getMessage(), 5000); // remove from here
            mySnackbar.show();
        }
    }

    public ThrowingSupplier<NavDirections> getNavDirectionSupplier() {
        return mNavDirectionSupplier;
    }
}

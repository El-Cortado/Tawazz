package com.example.tawazz.room;

import android.view.View;

import com.example.tawazz.utils.gui.NavigationOnClickListener;
import com.google.android.material.snackbar.Snackbar;

import java.util.Observable;
import java.util.Observer;

public class JoinRoomRequestObserver implements Observer {
    private final View mView;
    private final NavigationOnClickListener mNavigationOnClickListener;

    public JoinRoomRequestObserver(View view, NavigationOnClickListener navigationOnClickListener) {
        mView = view;
        mNavigationOnClickListener = navigationOnClickListener;
    }

    @Override
    public void update(Observable observable, Object isRoomIdExist) {
        if (!(Boolean) isRoomIdExist) {
            Snackbar mySnackbar = Snackbar.make(mView, "invalid UUID", 5000); // remove from here
            mySnackbar.show();
        } else {
            mNavigationOnClickListener.onClick(mView);
        }
    }
}

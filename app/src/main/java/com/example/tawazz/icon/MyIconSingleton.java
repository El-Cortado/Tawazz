package com.example.tawazz.icon;

import android.net.Uri;

public class MyIconSingleton {

    private static Icon sInstance;

    public static Icon getInstance() {
        if (sInstance == null) {
            sInstance = new Icon(Uri.parse("android.resource://com.example.tawazz/drawable/albert_einstein"));
        }
        return sInstance;
    }

    public static void init(Icon icon) {
        sInstance = icon;
    }

}

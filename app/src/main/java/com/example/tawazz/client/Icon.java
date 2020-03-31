package com.example.tawazz.client;

import android.net.Uri;

public class Icon {
    private final Uri mIconPath;

    public Icon(Uri mIconPath) {
        this.mIconPath = mIconPath;
    }

    public Uri getIconPath() {
        return mIconPath;
    }
}

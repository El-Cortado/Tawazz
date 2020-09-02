package com.example.tawazz.utils;

import android.content.Context;
import android.content.res.Resources;

public class ResourcesSingleton {
    public static Resources sInstance;

    public static void init(Context context) {
        sInstance = context.getResources();
    }

    public static Resources getInstance() {
        if (sInstance == null) {
            throw new IllegalStateException("Singleton uninitialized");
        }
        return sInstance;
    }

}

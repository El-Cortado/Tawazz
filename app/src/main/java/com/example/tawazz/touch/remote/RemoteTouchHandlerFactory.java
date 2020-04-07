package com.example.tawazz.touch.remote;

import android.content.Context;
import android.widget.ImageView;

import com.example.tawazz.touch.TouchStatusHandler;
import com.example.tawazz.user.User;

public class RemoteTouchHandlerFactory {
    private Context mContext;

    public RemoteTouchHandlerFactory(Context context) {
        this.mContext = context;
    }

    public TouchStatusHandler create(ImageView imageView) {
        return new TouchStatusHandler(imageView);
    }
}

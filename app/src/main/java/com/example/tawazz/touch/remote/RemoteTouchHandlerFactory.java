package com.example.tawazz.touch.remote;

import com.example.tawazz.icon.gui.RemoteIconBuilder;
import com.example.tawazz.touch.TouchStatusHandler;
import com.example.tawazz.user.User;

public class RemoteTouchHandlerFactory {
    private RemoteIconBuilder mRemoteIconBuilder;

    public RemoteTouchHandlerFactory(RemoteIconBuilder mRemoteIconBuilder) {
        this.mRemoteIconBuilder = mRemoteIconBuilder;
    }

    public TouchStatusHandler create(User user) {
        return new TouchStatusHandler(mRemoteIconBuilder.build(user.getId()));
    }
}

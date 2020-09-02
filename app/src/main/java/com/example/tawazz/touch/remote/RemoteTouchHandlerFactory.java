package com.example.tawazz.touch.remote;

import com.example.tawazz.icon.gui.RemoteIconBuilder;
import com.example.tawazz.touch.TouchStatusHandler;
import com.example.tawazz.user.User;

public class RemoteTouchHandlerFactory {
    private RemoteIconBuilder mRemoteIconBuilder;

    public RemoteTouchHandlerFactory(RemoteIconBuilder remoteIconBuilder) {
        this.mRemoteIconBuilder = remoteIconBuilder;
    }

    public TouchStatusHandler create(User user) {
        return new TouchStatusHandler(mRemoteIconBuilder.build(user.getId()));
    }
}

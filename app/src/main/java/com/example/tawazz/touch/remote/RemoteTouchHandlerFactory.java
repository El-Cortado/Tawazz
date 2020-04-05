package com.example.tawazz.touch.remote;

import com.example.tawazz.user.User;

public class RemoteTouchHandlerFactory {

    public RemoteTouchHandler create(User user) {
        return new RemoteTouchHandler(user);
    }
}

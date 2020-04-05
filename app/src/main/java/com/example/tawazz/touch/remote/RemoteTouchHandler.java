package com.example.tawazz.touch.remote;

import com.example.tawazz.touch.TouchStatus;
import com.example.tawazz.user.User;
import com.example.tawazz.utils.Handler;

public class RemoteTouchHandler implements Handler<TouchStatus> {
    private User mUser;

    public RemoteTouchHandler(User mUser) {
        this.mUser = mUser;
    }

    @Override
    public void handle(TouchStatus touchStatus) {

    }
}

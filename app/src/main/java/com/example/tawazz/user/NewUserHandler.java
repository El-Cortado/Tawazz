package com.example.tawazz.user;

import android.util.Log;

import com.example.tawazz.consts.Constants;
import com.example.tawazz.database.DocHandler;
import com.example.tawazz.icon.gui.RemoteIconBuilder;
import com.example.tawazz.touch.remote.RemoteTouchHandlerFactory;
import com.example.tawazz.touch.remote.RemoteTouchListener;

import java.util.Map;
import java.util.UUID;

public class NewUserHandler implements DocHandler {
    private Map<UUID, User> mSignedInUsers;
    private User mLocalUser;
    private RemoteTouchListener mRemoteTouchListener;
    private RemoteTouchHandlerFactory mRemoteTouchHandlerFactory;

    public NewUserHandler(Map<UUID, User> signedInUsers, User localUser, RemoteTouchListener remoteTouchListener, RemoteTouchHandlerFactory remoteTouchHandlerFactory) {
        this.mSignedInUsers = signedInUsers;
        this.mLocalUser = localUser;
        this.mRemoteTouchListener = remoteTouchListener;
        this.mRemoteTouchHandlerFactory = remoteTouchHandlerFactory;
    }

    @Override
    public void handle(Map map) {
        Log.d(Constants.TAWAZZ_LOG_TAG, "handling a new user");

        UUID userId = UUID.fromString((String)map.get(Constants.USER_ID_READABLE_DATABASE_KEY));

        if (!mSignedInUsers.containsKey(userId) && !userId.equals(mLocalUser.getId())) {
            User newUser = new User(userId, mLocalUser.getRoomId());

            mSignedInUsers.put(userId, newUser);
            mRemoteTouchListener.addUserTouchListener(newUser, mRemoteTouchHandlerFactory.create(newUser));
        }
    }
}

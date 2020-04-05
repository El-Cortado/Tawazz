package com.example.tawazz.user;

import android.util.Log;

import com.example.tawazz.consts.Constants;
import com.example.tawazz.database.DocHandler;
import com.example.tawazz.icon.Icon;
import com.example.tawazz.icon.IconRepository;

import java.util.Map;
import java.util.UUID;

public class NewUserHandler implements DocHandler {
    private IconRepository mIconRepository;
    private Map<UUID, User> mSignedInUsers;
    private User mLocalUser;

    public NewUserHandler(IconRepository mIconRepository, Map<UUID, User> mSignedInUsers, User mLocalUser) {
        this.mIconRepository = mIconRepository;
        this.mSignedInUsers = mSignedInUsers;
        this.mLocalUser = mLocalUser;
    }

    @Override
    public void handle(Map map) {
        Log.d(Constants.TAWAZZ_LOG_TAG, "handling a new user");

        UUID userId = UUID.fromString((String)map.get(Constants.USER_ID_READABLE_DATABASE_KEY));

        if (mSignedInUsers.containsKey(userId)) {
            Icon newUserIcon = mIconRepository.getUserIcon(userId);
            User newUser = new User(userId, mLocalUser.getRoomId(), newUserIcon);

            mSignedInUsers.put(userId, newUser);
        }

        // add listener to that user (through the firebase database)

    }
}

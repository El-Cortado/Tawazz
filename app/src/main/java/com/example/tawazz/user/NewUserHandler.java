package com.example.tawazz.user;

import com.example.tawazz.icon.Icon;
import com.example.tawazz.icon.IconRepository;

import java.util.Map;
import java.util.UUID;

public class NewUserHandler {
    private IconRepository mIconRepository;
    private Map<UUID, User> mSignedInUsers;
    private User mLocalUser;

    public NewUserHandler(IconRepository mIconRepository, Map<UUID, User> mSignedInUsers, User mLocalUser) {
        this.mIconRepository = mIconRepository;
        this.mSignedInUsers = mSignedInUsers;
        this.mLocalUser = mLocalUser;
    }

    public void handle(UUID userId) {
        if (mSignedInUsers.containsKey(userId)) {
            Icon newUserIcon = mIconRepository.getUserIcon(userId);
            User newUser = new User(userId, mLocalUser.getRoomId(), newUserIcon);

            mSignedInUsers.put(userId, newUser);
        }

        // add listener to that user (through the firebase database)

    }
}

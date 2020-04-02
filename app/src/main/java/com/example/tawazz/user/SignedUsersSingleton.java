package com.example.tawazz.user;

public class SignedUsersSingleton {
    private static SignedUsersMap sInstance;

    public static SignedUsersMap getInstance() {
        if (sInstance == null) {
            sInstance = new SignedUsersMap();
        }
        return sInstance;
    }
}

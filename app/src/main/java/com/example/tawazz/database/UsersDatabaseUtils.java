package com.example.tawazz.database;

import com.example.tawazz.consts.Constants;
import com.example.tawazz.user.User;

public class UsersDatabaseUtils {
    public String getUserDatabasePath(User user) {
        return getRoomPath(user) + "/" +
                Constants.USERS_DATABASE_KEY + "/" +
                user.getId().toString();
    }

    public String getRoomPath(User user) {
        return Constants.ROOMS_DATABASE_KEY + "/" +
                user.getRoomId().toString();
    }

}

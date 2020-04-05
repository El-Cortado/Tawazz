package com.example.tawazz.user;

import com.example.tawazz.consts.Constants;
import com.example.tawazz.database.Database;
import com.example.tawazz.database.ReadableDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class UserRepository {
    // todo: will be set in the room creation
    private final int USERS_LIMIT_AMOUNT = 10;

    private NewUserHandler mNewUserHandler;
    private Database mDatabase;
    private ReadableDatabase mReadableDatabase;

    public UserRepository(Map<UUID, User> mUsers, Database mDatabase, ReadableDatabase mReadableDatabase, NewUserHandler mNewUserHandler) {
        this.mNewUserHandler = mNewUserHandler;
        this.mDatabase = mDatabase;
        this.mReadableDatabase = mReadableDatabase;
    }

    public void add(User user) {
        Map<String, String> userMap = new HashMap<>();
        userMap.put(Constants.ROOM_ID_READABLE_DATABASE_KEY, user.getRoomId().toString());
        userMap.put(Constants.USER_ID_READABLE_DATABASE_KEY, user.getId().toString());

        mReadableDatabase.add(Constants.USERS_DATABASE_KEY, userMap);
    }

    public void initAllRoomUsers(UUID roomId) {
        mReadableDatabase.handleAllThatEqualTo(
                Constants.USERS_DATABASE_KEY, Constants.ROOM_ID_READABLE_DATABASE_KEY, roomId.toString(), mNewUserHandler);
    }

    public void updateUserField(User user, String field, Object object) {
        mDatabase.addToDatabase(object, getUserDatabasePath(user), field);
    }

    private String getUserDatabasePath(User user) {
        return getRoomPath(user) + "/" +
                Constants.USERS_DATABASE_KEY + "/" +
                user.getId().toString();
    }

    private String getRoomPath(User user) {
        return Constants.ROOMS_DATABASE_KEY + "/" +
                user.getRoomId().toString();
    }

}

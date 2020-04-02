package com.example.tawazz.user;

import com.example.tawazz.consts.Constants;
import com.example.tawazz.database.Database;
import com.example.tawazz.database.ReadableDatabase;
import com.example.tawazz.database.exceptions.FailedGettingAllDocsException;
import com.example.tawazz.user.exceptions.UserAlreadyExistException;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


public class UserRepository {
    // todo: will be set in the room creation
    private final int USERS_LIMIT_AMOUNT = 10;

    private Database mDatabase;
    private ReadableDatabase mReadableDatabase;

    public UserRepository(Map<UUID, User> mUsers, Database mDatabase, ReadableDatabase mReadableDatabase) {
        this.mDatabase = mDatabase;
        this.mReadableDatabase = mReadableDatabase;
    }

    public void add(User user) throws UserAlreadyExistException {
        Map<String, String> userMap = new HashMap<>();
        userMap.put(Constants.ROOM_ID_READABLE_DATABASE_KEY, user.getRoomId().toString());
        userMap.put(Constants.USER_ID_READABLE_DATABASE_KEY, user.getId().toString());

        mReadableDatabase.add(Constants.USERS_DATABASE_KEY, userMap);
    }

    public List<UUID> getAllRoomUsers(UUID roomId) throws FailedGettingAllDocsException {
        List<Map> usersObjects = mReadableDatabase.getAllThatEqualTo(
                Constants.USERS_DATABASE_KEY, Constants.ROOM_ID_READABLE_DATABASE_KEY, roomId.toString());

        return Lists.transform(usersObjects, new Function<Map, UUID>() {
            @Override
            public UUID apply(Map arg) {
                String userId = (String)arg.get(Constants.USER_ID_READABLE_DATABASE_KEY);
                return UUID.fromString(userId);
            }
        });
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

package com.example.tawazz.user;

import com.example.tawazz.database.Database;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class UserRepository {
    // todo: will be set in the group creation
    private final int USERS_LIMIT_AMOUNT = 10;

    private Map<UUID, User> mUsers;
    private Database mDatabase;

    public UserRepository(Map<UUID, User> mUsers, Database mDatabase) {
        this.mUsers = mUsers;
        this.mDatabase = mDatabase;
    }

    public synchronized void add(User user) {
        mUsers.put(user.getId(), user);
    }

    public synchronized User get(UUID userID) {
        return mUsers.get(userID);
    }

    public List<User> getAll() {
        Query query = mDatabase.getDatabaseRef().orderByKey();

        //todo: get the list of ids from the server and add location listener for each user

        return new ArrayList<>();
    }
}

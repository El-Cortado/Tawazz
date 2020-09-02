package com.example.tawazz.user;

import android.util.Log;

import com.example.tawazz.consts.Constants;
import com.example.tawazz.database.AddingToFirestoreException;
import com.example.tawazz.database.Database;
import com.example.tawazz.database.ReadableDatabase;
import com.example.tawazz.database.UsersDatabaseUtils;
import com.google.cloud.firestore.DocumentReference;
import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class UserRepository {
    // todo: will be set in the room creation
    private final int USERS_LIMIT_AMOUNT = 10;

    private NewUserHandler mNewUserHandler;
    private Database mDatabase;
    private ReadableDatabase mReadableDatabase;
    private UsersDatabaseUtils mUsersDatabaseUtils;
    private Map<UUID, DocumentReference> mUsersReadableDatabaseReferences;
    private Map<UUID, DatabaseReference> mUsersRealtimeDatabaseReferences;

    public UserRepository(Database database, ReadableDatabase readableDatabase, NewUserHandler newUserHandler, UsersDatabaseUtils usersDatabaseUtils) {
        this.mNewUserHandler = newUserHandler;
        this.mDatabase = database;
        this.mReadableDatabase = readableDatabase;
        this.mUsersDatabaseUtils = usersDatabaseUtils;
        this.mUsersReadableDatabaseReferences = new HashMap<>();
        this.mUsersRealtimeDatabaseReferences = new HashMap<>();
    }

    public void add(User user) throws AddingToFirestoreException {
        Map<String, String> userMap = new HashMap<>();
        userMap.put(Constants.ROOM_ID_READABLE_DATABASE_KEY, user.getRoomId().toString());
        userMap.put(Constants.USER_ID_READABLE_DATABASE_KEY, user.getId().toString());

        DocumentReference userDocumentReference = mReadableDatabase.add(Constants.USERS_DATABASE_KEY, userMap);
        mUsersReadableDatabaseReferences.put(user.getId(), userDocumentReference);
    }

    public void removeUser(User user) {
        DocumentReference userDocumentReference = mUsersReadableDatabaseReferences.get(user.getId());
        if (userDocumentReference != null) {
            userDocumentReference.delete();
            mUsersReadableDatabaseReferences.remove(user.getId());
        }
        DatabaseReference databaseReference = mUsersRealtimeDatabaseReferences.get(user.getId());
        if (databaseReference != null) {
            databaseReference.removeValue();
            mUsersRealtimeDatabaseReferences.remove(user.getId());
        }
    }

    public void initAllRoomUsers(UUID roomId) {
        mReadableDatabase.handleAllThatEqualTo(
                Constants.USERS_DATABASE_KEY, Constants.ROOM_ID_READABLE_DATABASE_KEY, roomId.toString(), mNewUserHandler);
    }

    public void updateUserField(User user, String field, Object object) {
        DatabaseReference databaseReference = mDatabase.addToDatabase(object, mUsersDatabaseUtils.getUserDatabasePath(user), field);
        mUsersRealtimeDatabaseReferences.put(user.getId(), databaseReference.getParent());
    }
}

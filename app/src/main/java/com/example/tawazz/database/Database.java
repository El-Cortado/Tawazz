package com.example.tawazz.database;

import android.graphics.Point;

import com.example.tawazz.client.User;
import com.example.tawazz.consts.Constants;
import com.example.tawazz.touch.TouchAction;
import com.google.firebase.database.DatabaseReference;

public class Database {
    private DatabaseReference mDatabase;

    public Database(DatabaseReference mDatabase) {
        this.mDatabase = mDatabase;
    }

    public void updateTouchLocation(Point newLocation, User user) {
        getUserDatabaseRef(user).child(Constants.WIDTH_DATABASE_KEY).setValue(newLocation.x);
        getUserDatabaseRef(user).child(Constants.HEIGHT_DATABASE_KEY).setValue(newLocation.y);
    }

    public void updateTouchAction(TouchAction touchAction, User user) {
        getUserDatabaseRef(user).child(Constants.TOUCH_ACTION_DATABASE_KEY).setValue(touchAction);
    }

    public void addUser(User user) {
        getUserDatabaseRef(user).child(Constants.USER_ID_DATABASE_KEY).setValue(user.getId().toString());
    }

    public DatabaseReference getDatabase() {
        return mDatabase;
    }

    private DatabaseReference getUserDatabaseRef(User user) {
        return mDatabase.child(Constants.ROOMS_DATABASE_KEY).child(user.getGroupId().toString())
                .child(Constants.USERS_DATABASE_KEY).child(user.getId().toString());
    }
}

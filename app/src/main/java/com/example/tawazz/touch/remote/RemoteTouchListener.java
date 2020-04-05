package com.example.tawazz.touch.remote;

import com.example.tawazz.database.Database;
import com.example.tawazz.database.UsersDatabaseUtils;
import com.example.tawazz.touch.DatabaseTouchStatusConverter;
import com.example.tawazz.user.User;
import com.example.tawazz.utils.Handler;

public class RemoteTouchListener {
    private Database mDatabase;
    private DatabaseTouchStatusConverter mDatabaseTouchStatusConverter;
    private UsersDatabaseUtils mUsersDatabaseUtils;

    public RemoteTouchListener(Database mDatabase, DatabaseTouchStatusConverter mDatabaseTouchStatusConverter, UsersDatabaseUtils mUsersDatabaseUtils) {
        this.mDatabase = mDatabase;
        this.mDatabaseTouchStatusConverter = mDatabaseTouchStatusConverter;
        this.mUsersDatabaseUtils = mUsersDatabaseUtils;
    }

    public void addUserTouchListener(User user, Handler handler) {
        mDatabase.addOnChangeTrigger(handler, mDatabaseTouchStatusConverter, mUsersDatabaseUtils.getUserDatabasePath(user));
    }
}

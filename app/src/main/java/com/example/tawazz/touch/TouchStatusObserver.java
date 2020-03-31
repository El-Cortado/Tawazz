package com.example.tawazz.touch;

import android.util.Log;

import com.example.tawazz.client.User;
import com.example.tawazz.consts.Constants;
import com.example.tawazz.database.Database;
import com.example.tawazz.touch.exceptions.FailedUpdatingServerException;

import java.util.Observable;
import java.util.Observer;

public class TouchStatusObserver implements Observer {
    private Database mDatabase;
    private User mUser;

    public TouchStatusObserver(Database mDatabase, User mUser) {
        this.mDatabase = mDatabase;
        this.mUser = mUser;
    }

    @Override
    public void update(Observable o, Object arg) {
        try {
            Log.e(Constants.TAWAZZ_LOG_TAG, "UPDATE");
            updateTouchStatus((TouchStatus) arg);
        } catch (FailedUpdatingServerException e) {
            Log.e(Constants.TAWAZZ_LOG_TAG, "Failed Updating Server", e);
        }
    }

    private void updateTouchStatus(TouchStatus touchStatus) throws FailedUpdatingServerException {
        mDatabase.updateTouchLocation(touchStatus.getmLocation(), mUser);
        mDatabase.updateTouchAction(touchStatus.getTouchAction(), mUser);
    }

}

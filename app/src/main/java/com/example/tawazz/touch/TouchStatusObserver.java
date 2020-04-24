package com.example.tawazz.touch;

import android.util.Log;

import com.example.tawazz.consts.Constants;
import com.example.tawazz.user.User;

import java.util.Observable;
import java.util.Observer;

public class TouchStatusObserver implements Observer {
    private User mUser;
    private TouchUpdater mTouchUpdater;

    public TouchStatusObserver(User user, TouchUpdater touchUpdater) {
        this.mUser = user;
        this.mTouchUpdater = touchUpdater;
    }

    @Override
    public void update(Observable o, Object arg) {
        Log.e(Constants.TAWAZZ_LOG_TAG, "UPDATE");
        updateTouchStatus((TouchStatus) arg);
    }

    private void updateTouchStatus(TouchStatus touchStatus) {
        mTouchUpdater.updateTouchLocation(touchStatus.getmLocation(), mUser);
        mTouchUpdater.updateTouchAction(touchStatus.getTouchAction(), mUser);
    }

}

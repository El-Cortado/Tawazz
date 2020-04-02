package com.example.tawazz.touch;

import android.util.Log;

import com.example.tawazz.user.User;
import com.example.tawazz.consts.Constants;
import com.example.tawazz.touch.exceptions.FailedUpdatingServerException;

import java.util.Observable;
import java.util.Observer;

public class TouchStatusObserver implements Observer {
    private User mUser;
    private TouchUpdater mTouchUpdater;

    public TouchStatusObserver(User mUser, TouchUpdater mTouchUpdater) {
        this.mUser = mUser;
        this.mTouchUpdater = mTouchUpdater;
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
        mTouchUpdater.updateTouchLocation(touchStatus.getmLocation(), mUser);
        mTouchUpdater.updateTouchAction(touchStatus.getTouchAction(), mUser);
    }

}

package com.example.tawazz.touch;

import android.util.Log;

import com.example.tawazz.communication.ServerCommunicator;
import com.example.tawazz.communication.exceptions.FailedUpdatingServerException;
import com.example.tawazz.consts.Constants;

import java.util.Observable;
import java.util.Observer;
import java.util.UUID;

public class TouchStatusObserver implements Observer {
    private final ServerCommunicator mServerCommunicator;

    public TouchStatusObserver(ServerCommunicator mServerCommunicator) {
        this.mServerCommunicator = mServerCommunicator;
    }

    @Override
    public void update(Observable o, Object arg) {
        try {
            Log.e(Constants.TAWAZZ_LOG_TAG, "UPDATE");
            mServerCommunicator.updateTouchStatus((TouchStatus) arg);
        } catch (FailedUpdatingServerException e) {
            Log.e(Constants.TAWAZZ_LOG_TAG, "Failed Updating Server", e);
        }
    }
}

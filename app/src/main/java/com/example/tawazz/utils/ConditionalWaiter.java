package com.example.tawazz.utils;

import android.util.Log;

import com.example.tawazz.consts.Constants;

import java.util.concurrent.TimeoutException;

public class ConditionalWaiter {
    private final int DEFAULT_TIMEOUT_MS = 10000;
    private final int INTERVAL_MS = 500;

    private final Condition mCondition;

    public ConditionalWaiter(Condition mCondition) {
        this.mCondition = mCondition;
    }

    public void waitTill(int timeout) throws FailedWaitingForCondition {
        long startingTime = System.currentTimeMillis();
        while (!mCondition.isConfirmed()) {
            try {
                Log.d(Constants.TAWAZZ_LOG_TAG, "----------------------");
                if (System.currentTimeMillis() > timeout + startingTime) {
                    throw new TimeoutException();
                }

                Thread.sleep(INTERVAL_MS);
            } catch (InterruptedException | TimeoutException e) {
                throw new FailedWaitingForCondition(e);
            }
        }
    }

    public void waitTill() throws FailedWaitingForCondition {
        waitTill(DEFAULT_TIMEOUT_MS);
    }

}

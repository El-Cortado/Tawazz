package com.example.tawazz.utils.condition;

import com.example.tawazz.utils.exceptions.FailedWaitingForConditionException;

import java.util.concurrent.TimeoutException;

public class ConditionalWaiter {
    private final int DEFAULT_TIMEOUT_MS = 20000;
    private final int INTERVAL_MS = 500;

    private final Condition mCondition;

    public ConditionalWaiter(Condition condition) {
        this.mCondition = condition;
    }

    public void waitTill(int timeout) throws FailedWaitingForConditionException {
        long startingTime = System.currentTimeMillis();
        while (!mCondition.isConfirmed()) {
            try {
                if (System.currentTimeMillis() > timeout + startingTime) {
                    throw new TimeoutException();
                }

                Thread.sleep(INTERVAL_MS);
            } catch (InterruptedException | TimeoutException e) {
                throw new FailedWaitingForConditionException(e);
            }
        }
    }

    public void waitTill() throws FailedWaitingForConditionException {
        waitTill(DEFAULT_TIMEOUT_MS);
    }

}

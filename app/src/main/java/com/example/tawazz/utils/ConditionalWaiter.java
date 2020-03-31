package com.example.tawazz.utils;

public class ConditionalWaiter {
    private final int DEFAULT_TIMEOUT = 10;
    private final int INTERVAL_MS = 500;
    private final Condition mCondition;

    public ConditionalWaiter(Condition mCondition) {
        this.mCondition = mCondition;
    }

    public void waitTill(int timeout) throws FailedWaitingForCondition{
        while (!mCondition.isConfirmed()) {
            try {
                Thread.sleep(INTERVAL_MS);
            } catch (InterruptedException e) {
                throw new FailedWaitingForCondition(e);
            }
        }
    }

    public void waitTill() throws FailedWaitingForCondition {
        waitTill(DEFAULT_TIMEOUT);
    }

}

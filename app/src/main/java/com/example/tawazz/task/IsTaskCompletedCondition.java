package com.example.tawazz.task;

import android.util.Log;

import com.example.tawazz.consts.Constants;
import com.example.tawazz.utils.Condition;
import com.google.android.gms.tasks.Task;

public class IsTaskCompletedCondition implements Condition {
    private final Task task;

    public IsTaskCompletedCondition(Task task) {
        this.task = task;
    }

    @Override
    public boolean isConfirmed() {
        Boolean b = task.isComplete();
        Log.d(Constants.TAWAZZ_LOG_TAG, Boolean.toString(b));
        return b;
    }
}

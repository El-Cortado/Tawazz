package com.example.tawazz.task;

import com.example.tawazz.utils.Condition;
import com.google.android.gms.tasks.Task;

public class IsTaskCompletedCondition implements Condition {
    private final Task task;

    public IsTaskCompletedCondition(Task task) {
        this.task = task;
    }

    @Override
    public boolean isConfirmed() {
        return task.isComplete();
    }
}

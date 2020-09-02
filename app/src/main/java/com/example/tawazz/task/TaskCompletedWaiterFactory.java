package com.example.tawazz.task;

import com.example.tawazz.utils.condition.ConditionalWaiter;
import com.google.android.gms.tasks.Task;

public class TaskCompletedWaiterFactory {
    public ConditionalWaiter create(Task task) {
        return new ConditionalWaiter(new IsTaskCompletedCondition(task));
    }

}

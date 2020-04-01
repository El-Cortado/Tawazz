package com.example.tawazz.task;

import com.example.tawazz.utils.ConditionalWaiter;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.UploadTask;

public class TaskSuccessfulWaiterFactory {
    public ConditionalWaiter create(Task task) {
        return new ConditionalWaiter(new IsTaskSuccessfulCondition(task));
    }
}

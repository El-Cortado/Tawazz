package com.example.tawazz.upload;

import com.example.tawazz.utils.ConditionalWaiter;
import com.google.firebase.storage.UploadTask;

public class UploadFinishedWaiterFactory {
    public ConditionalWaiter create(UploadTask uploadTask) {
        return new ConditionalWaiter(new IsUploadedCondition(uploadTask));
    }
}

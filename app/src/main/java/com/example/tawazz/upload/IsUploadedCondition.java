package com.example.tawazz.upload;

import com.example.tawazz.utils.Condition;
import com.google.firebase.storage.UploadTask;

public class IsUploadedCondition implements Condition {
    private final UploadTask mUploadTask;

    public IsUploadedCondition(UploadTask mUploadTask) {
        this.mUploadTask = mUploadTask;
    }

    @Override
    public boolean isConfirmed() {
        return mUploadTask.isSuccessful();
    }
}

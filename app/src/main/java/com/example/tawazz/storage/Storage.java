package com.example.tawazz.storage;

import android.net.Uri;

import com.example.tawazz.storage.exceptions.FailedStoreException;
import com.example.tawazz.upload.UploadFinishedWaiterFactory;
import com.example.tawazz.utils.FailedWaitingForCondition;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Storage {
    private final int UPLOAD_TIMEOUT = 10;
    private StorageReference storageReference;
    private UploadFinishedWaiterFactory mUploadFinishedWaiterFactory;

    public Storage(StorageReference storageReference, UploadFinishedWaiterFactory uploadFinishedWaiterFactory) {
        this.storageReference = storageReference;
        this.mUploadFinishedWaiterFactory = uploadFinishedWaiterFactory;
    }

    public UploadTask store(Uri srcDir, Uri destDir) {
        StorageReference userIconStorageRef = storageReference.child(destDir.getPath());
        return userIconStorageRef.putFile(srcDir);
    }

    public void safeStore(Uri srcDir, Uri destDir) throws FailedStoreException {
        try {
            UploadTask uploadTask = store(srcDir, destDir);
            mUploadFinishedWaiterFactory.create(uploadTask).waitTill();
        } catch (FailedWaitingForCondition e) {
            throw new FailedStoreException(e);
        }
    }
}

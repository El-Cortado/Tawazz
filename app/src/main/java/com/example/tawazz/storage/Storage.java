package com.example.tawazz.storage;

import android.net.Uri;

import com.example.tawazz.download.DownloadInvoker;
import com.example.tawazz.download.ExtensionType;
import com.example.tawazz.storage.exceptions.FailedStoreException;
import com.example.tawazz.task.TaskCompletedWaiterFactory;
import com.example.tawazz.utils.FailedWaitingForCondition;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Storage {
    private StorageReference mStorageReference;
    private TaskCompletedWaiterFactory mTaskCompletedWaiterFactory;
    private DownloadInvoker mDownloadInvoker;

    public Storage(StorageReference storageReference, TaskCompletedWaiterFactory taskCompletedWaiterFactory, DownloadInvoker mDownloadInvoker) {
        this.mStorageReference = storageReference;
        this.mTaskCompletedWaiterFactory = taskCompletedWaiterFactory;
        this.mDownloadInvoker = mDownloadInvoker;
    }

    public UploadTask store(Uri srcDir, Uri destDir) throws FailedStoreException {
        StorageReference userIconStorageRef = mStorageReference.child(destDir.getPath());
        return userIconStorageRef.putFile(srcDir);
    }

    public void safeStore(Uri srcDir, Uri destDir) throws FailedStoreException {
        try {
            UploadTask uploadTask = store(srcDir, destDir);
            mTaskCompletedWaiterFactory.create(uploadTask).waitTill();
        } catch (FailedWaitingForCondition e) {
            throw new FailedStoreException(e);
        }
    }

    public Uri get(final Uri storedDataUri, final String fileName, final ExtensionType extensionType, final Uri desDir) {
        StorageReference ref = mStorageReference.child(storedDataUri.getPath());
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                mDownloadInvoker.downloadUriFiles(fileName, extensionType.toString(), desDir.getPath(), uri);
            }
        });

        return Uri.parse(desDir.toString() + "/" + fileName + extensionType.toString());
    }

    public StorageReference getStorageReference() {
        return mStorageReference;
    }
}

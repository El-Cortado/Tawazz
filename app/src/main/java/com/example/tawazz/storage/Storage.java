package com.example.tawazz.storage;

import android.net.Uri;

import com.example.tawazz.download.DownloadInvoker;
import com.example.tawazz.download.ExtensionType;
import com.example.tawazz.storage.exceptions.FailedStoreException;
import com.example.tawazz.task.TaskSuccessfulWaiterFactory;
import com.example.tawazz.utils.FailedWaitingForCondition;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Storage {
    private StorageReference mStorageReference;
    private TaskSuccessfulWaiterFactory mTaskSuccessfulWaiterFactory;
    private DownloadInvoker mDownloadInvoker;

    public Storage(StorageReference storageReference, TaskSuccessfulWaiterFactory taskSuccessfulWaiterFactory, DownloadInvoker mDownloadInvoker) {
        this.mStorageReference = storageReference;
        this.mTaskSuccessfulWaiterFactory = taskSuccessfulWaiterFactory;
        this.mDownloadInvoker = mDownloadInvoker;
    }

    public UploadTask store(Uri srcDir, Uri destDir) {
        StorageReference userIconStorageRef = mStorageReference.child(destDir.getPath());
        return userIconStorageRef.putFile(srcDir);
    }

    public void safeStore(Uri srcDir, Uri destDir) throws FailedStoreException {
        try {
            UploadTask uploadTask = store(srcDir, destDir);
            mTaskSuccessfulWaiterFactory.create(uploadTask).waitTill();
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

    public StorageReference getmStorageReference() {
        return mStorageReference;
    }
}

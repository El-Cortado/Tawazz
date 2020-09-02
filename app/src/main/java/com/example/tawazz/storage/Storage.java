package com.example.tawazz.storage;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.example.tawazz.storage.exceptions.FailedStoreException;
import com.example.tawazz.task.TaskCompletedWaiterFactory;
import com.example.tawazz.utils.exceptions.FailedWaitingForConditionException;
import com.example.tawazz.utils.firebase.GlideApp;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Storage {
    private StorageReference mStorageReference;
    private TaskCompletedWaiterFactory mTaskCompletedWaiterFactory;

    public Storage(StorageReference storageReference, TaskCompletedWaiterFactory taskCompletedWaiterFactory) {
        this.mStorageReference = storageReference;
        this.mTaskCompletedWaiterFactory = taskCompletedWaiterFactory;
    }

    public StorageReference safeStore(Uri srcDir, Uri destDir) throws FailedStoreException {
        try {
            StorageReference userIconStorageRef = mStorageReference.child(destDir.getPath());
            UploadTask uploadTask = userIconStorageRef.putFile(srcDir);
            mTaskCompletedWaiterFactory.create(uploadTask).waitTill();

            return userIconStorageRef;
        } catch (FailedWaitingForConditionException e) {
            throw new FailedStoreException(e);
        }
    }

//    (!) unused
//    public Uri get(final Uri storedDataUri, final String fileName, final ExtensionType extensionType, final Uri desDir) {
//        StorageReference ref = mStorageReference.child(storedDataUri.getPath());
//        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//            @Override
//            public void onSuccess(Uri uri) {
//                mDownloadInvoker.downloadUriFiles(fileName, extensionType.getExtensionName(), desDir.getPath(), uri);
//            }
//        });
//
//        return Uri.parse(desDir.toString() + "/" + fileName + extensionType.getExtensionName());
//    }

    public void loadImageInto(Uri imageUri, ImageView imageView, Context context) {
        GlideApp.with(context).load(mStorageReference.child(imageUri.getPath())).into(imageView);
    }

}

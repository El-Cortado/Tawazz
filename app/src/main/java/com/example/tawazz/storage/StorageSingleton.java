package com.example.tawazz.storage;

import android.content.Context;

import com.example.tawazz.download.DownloadInvoker;
import com.example.tawazz.upload.UploadFinishedWaiterFactory;
import com.google.firebase.storage.FirebaseStorage;

public class StorageSingleton {
    private static Storage sInstance;

    public static Storage getInstance(Context context) {
        if (sInstance == null) {
            FirebaseStorage storage = FirebaseStorage.getInstance();
            DownloadInvoker downloadInvoker = new DownloadInvoker(context);
            sInstance = new Storage(storage.getReference(), new UploadFinishedWaiterFactory(), downloadInvoker);
        }
        return sInstance;
    }
}

package com.example.tawazz.storage;

import android.content.Context;

import com.example.tawazz.download.UriFilesDownloader;
import com.example.tawazz.task.TaskCompletedWaiterFactory;
import com.google.firebase.storage.FirebaseStorage;

public class StorageSingleton {
    private static Storage sInstance;

    public static Storage getInstance(Context context) {
        if (sInstance == null) {
            FirebaseStorage storage = FirebaseStorage.getInstance();
            UriFilesDownloader uriFilesDownloader = new UriFilesDownloader(context);
            sInstance = new Storage(storage.getReference(), new TaskCompletedWaiterFactory(), uriFilesDownloader);
        }
        return sInstance;
    }
}

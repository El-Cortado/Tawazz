package com.example.tawazz.storage;

import com.example.tawazz.task.TaskCompletedWaiterFactory;
import com.google.firebase.storage.FirebaseStorage;

public class StorageSingleton {
    private static Storage sInstance;

    public static Storage getInstance() {
        if (sInstance == null) {
            FirebaseStorage storage = FirebaseStorage.getInstance();
            sInstance = new Storage(storage.getReference(), new TaskCompletedWaiterFactory());
        }
        return sInstance;
    }
}

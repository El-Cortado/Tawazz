package com.example.tawazz.icon;

import android.util.Log;

import com.example.tawazz.consts.Constants;
import com.example.tawazz.icon.exceptions.FailedUpdateUserIconException;
import com.example.tawazz.storage.Storage;
import com.example.tawazz.storage.exceptions.FailedStoreException;
import com.example.tawazz.user.User;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

public class IconRepository {
//    private static String USER_ICON_DIR_PATH = "android.resource://com.example.tawazz/drawable";
    private static String USER_ICON_DIR_PATH = DIRECTORY_DOWNLOADS;

    private IconsDatabaseUtils mIconsDatabaseUtils;
    private Storage mStorage;
    private Map<UUID, StorageReference> mIconsStorageReferences;

    public IconRepository(IconsDatabaseUtils iconsDatabaseUtils, Storage storage) {
        this.mIconsDatabaseUtils = iconsDatabaseUtils;
        this.mStorage = storage;
        this.mIconsStorageReferences = new HashMap<>();
    }

    public void addUserIcon(User user) throws FailedUpdateUserIconException {
        try {
            StorageReference iconStorageReference = mStorage.safeStore(user.getIcon().getIconPath(), mIconsDatabaseUtils.getIconDir(user.getId()));
            mIconsStorageReferences.put(user.getId(), iconStorageReference);
        } catch (FailedStoreException e) {
            throw new FailedUpdateUserIconException(e);
        }
    }

    public void removeUserIcon(User user) {
        StorageReference storageReference = mIconsStorageReferences.get(user.getId());
        if (storageReference != null) {
            storageReference.delete();
        }
    }
}

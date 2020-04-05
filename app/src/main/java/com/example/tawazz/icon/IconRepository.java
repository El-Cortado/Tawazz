package com.example.tawazz.icon;

import android.net.Uri;

import com.example.tawazz.task.TaskCompletedWaiterFactory;
import com.example.tawazz.user.User;
import com.example.tawazz.consts.Constants;
import com.example.tawazz.download.ExtensionType;
import com.example.tawazz.icon.exceptions.FailedUpdateUserIconException;
import com.example.tawazz.storage.Storage;
import com.example.tawazz.storage.exceptions.FailedStoreException;

import java.util.UUID;

public class IconRepository {
    private static String USER_ICON_DIR_PATH = "android.resource://com.example.tawazz/drawable";

    private Storage mStorage;
    private TaskCompletedWaiterFactory mTaskCompletedWaiterFactory;

    public IconRepository(Storage mStorage, TaskCompletedWaiterFactory mTaskCompletedWaiterFactory) {
        this.mStorage = mStorage;
        this.mTaskCompletedWaiterFactory = mTaskCompletedWaiterFactory;
    }

    public Icon getUserIcon(UUID userUuid) {
        Uri iconUri = mStorage.get(getIconDir(userUuid), userUuid.toString(), ExtensionType.JPG, Uri.parse(USER_ICON_DIR_PATH));
        return new Icon(iconUri);
    }

    public void addUserIcon(User user) throws FailedUpdateUserIconException {
        try {
            mStorage.safeStore(user.getIcon().getIconPath(), getIconDir(user.getId()));
        } catch (FailedStoreException e) {
            throw new FailedUpdateUserIconException(e);
        }
    }

    private Uri getIconDir(UUID userId) {
        return Uri.parse(Constants.ICON_STORAGE_DIR + "/" + userId.toString());
    }

}

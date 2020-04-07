package com.example.tawazz.icon;

import android.util.Log;

import com.example.tawazz.consts.Constants;
import com.example.tawazz.icon.exceptions.FailedUpdateUserIconException;
import com.example.tawazz.storage.Storage;
import com.example.tawazz.storage.exceptions.FailedStoreException;
import com.example.tawazz.user.User;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

public class IconRepository {
//    private static String USER_ICON_DIR_PATH = "android.resource://com.example.tawazz/drawable";
    private static String USER_ICON_DIR_PATH = DIRECTORY_DOWNLOADS;

    private IconsDatabaseUtils mIconsDatabaseUtils;
    private Storage mStorage;

    public IconRepository(IconsDatabaseUtils mIconsDatabaseUtils, Storage mStorage) {
        this.mIconsDatabaseUtils = mIconsDatabaseUtils;
        this.mStorage = mStorage;
    }

    public void addUserIcon(User user) throws FailedUpdateUserIconException {
        try {
            Log.d(Constants.TAWAZZ_LOG_TAG, "------------------------------------------------");
            mStorage.safeStore(user.getIcon().getIconPath(), mIconsDatabaseUtils.getIconDir(user.getId()));
        } catch (FailedStoreException e) {
            throw new FailedUpdateUserIconException(e);
        }
    }

}

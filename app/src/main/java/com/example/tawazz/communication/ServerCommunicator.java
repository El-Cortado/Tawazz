package com.example.tawazz.communication;

import android.net.Uri;

import com.example.tawazz.client.User;
import com.example.tawazz.communication.exceptions.FailedSignInUserException;
import com.example.tawazz.communication.exceptions.FailedUpdatingServerException;
import com.example.tawazz.consts.Constants;
import com.example.tawazz.database.Database;
import com.example.tawazz.storage.Storage;
import com.example.tawazz.storage.exceptions.FailedStoreException;
import com.example.tawazz.touch.TouchStatus;

public class ServerCommunicator {
    //todo: MAX_USER_HISTORY_STEPS
    private Database mDatabase;
    private Storage mStorage;
    private User mUser;

    public ServerCommunicator(Database database, Storage mStorage, User mUser) {
        this.mDatabase = database;
        this.mStorage = mStorage;
        this.mUser = mUser;
    }

    public void signInUser() throws FailedSignInUserException {
        try {
            mStorage.safeStore(mUser.getIcon().getIconPath(), getIconDir(mUser));
        } catch (FailedStoreException e) {
            throw new FailedSignInUserException(e);
        }
    }

    public void updateTouchStatus(TouchStatus touchStatus) throws FailedUpdatingServerException {
        mDatabase.updateTouchLocation(touchStatus.getmLocation(), mUser);
        mDatabase.updateTouchAction(touchStatus.getTouchAction(), mUser);
    }

    public void authenticate() {
        // firebase shit
    }

    private Uri getIconDir(User user) {
        return Uri.parse(Constants.ICON_STORAGE_DIR + "/" + user.getId().toString());
    }

}

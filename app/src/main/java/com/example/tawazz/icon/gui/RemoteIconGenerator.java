package com.example.tawazz.icon.gui;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.example.tawazz.consts.Constants;
import com.example.tawazz.icon.IconsDatabaseUtils;
import com.example.tawazz.storage.Storage;

import java.util.UUID;

public class RemoteIconGenerator {
    private Context mContext;
    private Storage mStorage;
    private IconsDatabaseUtils mIconsDatabaseUtils;

    public RemoteIconGenerator(Context mContext, Storage mStorage, IconsDatabaseUtils mIconsDatabaseUtils) {
        this.mContext = mContext;
        this.mStorage = mStorage;
        this.mIconsDatabaseUtils = mIconsDatabaseUtils;
    }

    public ImageView getUserIcon(UUID userUuid) {
        Log.d(Constants.TAWAZZ_LOG_TAG, "------------------------------------------------");

        ImageView imageView = new ImageView(mContext);
        mStorage.loadImageInto(mIconsDatabaseUtils.getIconDir(userUuid), imageView, mContext);
        //        Uri iconUri = mStorage.get(getIconDir(userUuid), userUuid.toString(), ExtensionType.JPG, Uri.parse(USER_ICON_DIR_PATH));
        return imageView;
    }

}

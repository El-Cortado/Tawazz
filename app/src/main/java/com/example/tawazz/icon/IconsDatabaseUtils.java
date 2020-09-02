package com.example.tawazz.icon;

import android.net.Uri;

import com.example.tawazz.consts.Constants;

import java.util.UUID;

public class IconsDatabaseUtils {
    public Uri getIconDir(UUID userId) {
        return Uri.parse(Constants.ICON_STORAGE_DIR + "/" + userId.toString());
    }

}

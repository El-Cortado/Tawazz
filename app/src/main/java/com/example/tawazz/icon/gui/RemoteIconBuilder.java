package com.example.tawazz.icon.gui;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.tawazz.R;
import com.example.tawazz.consts.Constants;
import com.example.tawazz.icon.IconsDatabaseUtils;
import com.example.tawazz.storage.Storage;

import java.util.UUID;

public class RemoteIconBuilder {
    private Context mContext;
    private View view;
    private Storage mStorage;
    private IconsDatabaseUtils mIconsDatabaseUtils;

    public RemoteIconBuilder(Context mContext, View view, Storage mStorage, IconsDatabaseUtils mIconsDatabaseUtils) {
        this.mContext = mContext;
        this.view = view;
        this.mStorage = mStorage;
        this.mIconsDatabaseUtils = mIconsDatabaseUtils;
    }

    public ImageView build(UUID userUuid) {
        Log.d(Constants.TAWAZZ_LOG_TAG, "------------------------------------------------");

        ImageView imageView = new ImageView(mContext);
        ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(100, 100);
//        params.gravity = Gravity.TOP;

        imageView.setLayoutParams(params);
//        view.findViewById(R.id.albert_einstein);
        LinearLayout linearLayout = view.findViewById(R.id.user_icon_layout);
        linearLayout.addView(imageView);

        mStorage.loadImageInto(mIconsDatabaseUtils.getIconDir(userUuid), imageView, mContext);
        //        Uri iconUri = mStorage.get(getIconDir(userUuid), userUuid.toString(), ExtensionType.JPG, Uri.parse(USER_ICON_DIR_PATH));

        return imageView;
    }

}

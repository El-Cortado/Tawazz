package com.example.tawazz.icon.gui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.tawazz.R;
import com.example.tawazz.icon.IconsDatabaseUtils;
import com.example.tawazz.storage.Storage;

import java.util.UUID;

public class RemoteIconBuilder {
    private static final int ICON_LAYOUT_SIZE = 200;

    private Context mContext;
    private View view;
    private Storage mStorage;
    private IconsDatabaseUtils mIconsDatabaseUtils;

    public RemoteIconBuilder(Context context, View view, Storage storage, IconsDatabaseUtils iconsDatabaseUtils) {
        this.mContext = context;
        this.view = view;
        this.mStorage = storage;
        this.mIconsDatabaseUtils = iconsDatabaseUtils;
    }

    public ImageView build(UUID userUuid) {
        ImageView imageView = new ImageView(mContext);
        ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(ICON_LAYOUT_SIZE, ICON_LAYOUT_SIZE);
        imageView.setLayoutParams(params);
        LinearLayout linearLayout = view.findViewById(R.id.user_icon_layout);
        linearLayout.addView(imageView);

        mStorage.loadImageInto(mIconsDatabaseUtils.getIconDir(userUuid), imageView, mContext);

        return imageView;
    }

}

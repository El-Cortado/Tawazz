package com.example.tawazz.icon.gui;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.tawazz.databinding.RaffleRoomBinding;
import com.example.tawazz.icon.IconsDatabaseUtils;
import com.example.tawazz.storage.Storage;

import java.util.UUID;

public class RemoteIconBuilder {
    private static final int ICON_LAYOUT_SIZE = 200;

    private Context mContext;
    private Storage mStorage;
    private IconsDatabaseUtils mIconsDatabaseUtils;
    private RaffleRoomBinding mRaffleRoomBinding;

    public RemoteIconBuilder(Context context, RaffleRoomBinding raffleRoomBinding, Storage storage, IconsDatabaseUtils iconsDatabaseUtils) {
        this.mContext = context;
        this.mStorage = storage;
        this.mIconsDatabaseUtils = iconsDatabaseUtils;
        this.mRaffleRoomBinding = raffleRoomBinding;
    }

    public ImageView build(UUID userUuid) {
        ImageView imageView = new ImageView(mContext);
        ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(ICON_LAYOUT_SIZE, ICON_LAYOUT_SIZE);
        imageView.setLayoutParams(params);
        LinearLayout linearLayout = mRaffleRoomBinding.userIconLayout;
        linearLayout.addView(imageView);

        mStorage.loadImageInto(mIconsDatabaseUtils.getIconDir(userUuid), imageView, mContext);

        return imageView;
    }

}

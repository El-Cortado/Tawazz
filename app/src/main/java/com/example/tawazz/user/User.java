package com.example.tawazz.user;

import com.example.tawazz.icon.Icon;

import java.util.UUID;

public class User {
    private UUID mId;
    private UUID mRoomId;
    private Icon mIcon;

    public User(UUID id, UUID mRoomId) {
        this.mRoomId = mRoomId;
        this.mId = id;
    }

    public User(UUID mRoomId, Icon mIcon) {
        this.mRoomId = mRoomId;
        this.mIcon = mIcon;
    }

    public User(UUID id, UUID mRoomId, Icon mIcon) {
        this(mRoomId, mIcon);
        mId = id;
    }

    public void generateId() {
        mId = UUID.randomUUID();
    }

    public UUID getId() {
        return mId;
    }

    public UUID getRoomId() {
        return mRoomId;
    }

    public Icon getIcon() {
        return mIcon;
    }
}

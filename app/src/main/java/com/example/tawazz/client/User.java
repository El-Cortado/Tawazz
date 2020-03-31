package com.example.tawazz.client;

import com.example.tawazz.icon.Icon;

import java.util.UUID;

public class User {
    private UUID mId;
    private UUID mGroupId;
    private Icon mIcon;

    public User(UUID mGroupId, Icon mIcon) {
        this.mGroupId = mGroupId;
        this.mIcon = mIcon;
    }

    public void generateId() {
        mId = UUID.randomUUID();
    }

    public UUID getId() {
        return mId;
    }

    public UUID getGroupId() {
        return mGroupId;
    }

    public Icon getIcon() {
        return mIcon;
    }
}

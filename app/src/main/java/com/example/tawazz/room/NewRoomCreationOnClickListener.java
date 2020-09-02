package com.example.tawazz.room;

import android.view.View;

import com.example.tawazz.consts.Constants;
import com.example.tawazz.database.AddingToFirestoreException;
import com.example.tawazz.database.ReadableDatabase;
import com.example.tawazz.utils.exceptions.SupplyingException;
import com.example.tawazz.utils.gui.NavigationOnClickListener;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class NewRoomCreationOnClickListener implements View.OnClickListener {
    private final ReadableDatabase mReadableDatabase;
    private final NavigationOnClickListener mNavigationOnClickListener;
    private final UUID mRoomId;

    public NewRoomCreationOnClickListener(ReadableDatabase mReadableDatabase, NavigationOnClickListener navigationOnClickListener, UUID roomId) {
        this.mReadableDatabase = mReadableDatabase;
        this.mNavigationOnClickListener = navigationOnClickListener;
        this.mRoomId = roomId;
    }

    @Override
    public void onClick(View view) {
        try {
            Map<String, String> roomDoc = new HashMap<>();
            roomDoc.put(Constants.ROOM_ID_READABLE_DATABASE_KEY, mRoomId.toString());
            mReadableDatabase.add(Constants.ROOMS_DATABASE_KEY, roomDoc);
            mNavigationOnClickListener.onClick(view);
        } catch (AddingToFirestoreException e) {
            e.printStackTrace();
        }
    }
}

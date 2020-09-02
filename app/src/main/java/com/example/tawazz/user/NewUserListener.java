package com.example.tawazz.user;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tawazz.consts.Constants;
import com.example.tawazz.icon.Icon;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class NewUserListener implements ChildEventListener {
    private NewUserHandler mNewUserHandler;

    public NewUserListener(NewUserHandler newUserHandler) {
        this.mNewUserHandler = newUserHandler;
    }

    @Override
    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        UUID userId = UUID.fromString(dataSnapshot.getKey());
        Map<String, String> userDoc = new HashMap<>();
        userDoc.put(Constants.USER_ID_READABLE_DATABASE_KEY, userId.toString());
        mNewUserHandler.handle(userDoc);
    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}

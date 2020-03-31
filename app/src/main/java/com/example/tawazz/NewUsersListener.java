package com.example.tawazz;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tawazz.communication.ServerCommunicator;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.UUID;

public class NewUsersListener implements ChildEventListener {
    private ServerCommunicator mServerCommunicator;

    public NewUsersListener(ServerCommunicator mServerCommunicator) {
        this.mServerCommunicator = mServerCommunicator;
    }

    @Override
    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        UUID userId = UUID.fromString(dataSnapshot.getKey());
        mServerCommunicator.downloadUserIcon(userId);
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

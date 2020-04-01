package com.example.tawazz;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tawazz.icon.Icon;
import com.example.tawazz.icon.IconRepository;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.UUID;

public class NewUserListener implements ChildEventListener {
    private IconRepository mIconRepository;

    public NewUserListener(IconRepository mIconRepository) {
        this.mIconRepository = mIconRepository;
    }

    @Override
    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        UUID userId = UUID.fromString(dataSnapshot.getKey());
        Icon newUserIcon = mIconRepository.getUserIcon(userId);

        // add listener to that user (through the firebase database)
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

package com.example.tawazz.database;

import androidx.annotation.NonNull;

import com.example.tawazz.utils.Handler;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class Database {
    private DatabaseReference mDatabase;

    public Database(DatabaseReference mDatabase) {
        this.mDatabase = mDatabase;
    }

    public void addToDatabase(Object value, String ...path) {
        DatabaseReference ref = getPathRef(path);
        ref.setValue(value);
    }

    public void addOnChangeTrigger(
            final Handler handler, final DatabaseDataConvertor databaseDataConvertor, String ...path) {
        DatabaseReference ref = getPathRef(path);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                handler.handle(databaseDataConvertor.convert(dataSnapshot));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private DatabaseReference getPathRef(String ...path) {
        DatabaseReference ref = mDatabase;
        for (String pathParts: path) {
            ref = ref.child(pathParts);
        }
        return ref;
    }
}

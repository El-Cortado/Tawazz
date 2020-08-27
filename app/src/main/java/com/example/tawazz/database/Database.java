package com.example.tawazz.database;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.tawazz.consts.Constants;
import com.example.tawazz.utils.Handler;
import com.example.tawazz.utils.exceptions.FailedConvertingObjectException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class Database {
    private DatabaseReference mDatabaseReference;

    public Database(DatabaseReference databaseReference) {
        this.mDatabaseReference = databaseReference;
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
                try {
                    handler.handle(databaseDataConvertor.convert(dataSnapshot));
                } catch (FailedConvertingObjectException e) {
                    Log.e(Constants.TAWAZZ_LOG_TAG, "Failed Converting data from snapshot", e);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private DatabaseReference getPathRef(String ...path) {
        DatabaseReference ref = mDatabaseReference;
        for (String pathPart: path) {
            ref = ref.child(pathPart);
        }
        return ref;
    }
}

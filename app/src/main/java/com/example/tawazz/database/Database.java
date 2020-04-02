package com.example.tawazz.database;

import com.google.firebase.database.DatabaseReference;

public class Database {
    private DatabaseReference mDatabase;

    public Database(DatabaseReference mDatabase) {
        this.mDatabase = mDatabase;
    }

    public void addToDatabase(Object value, String ...path) {
        DatabaseReference ref = mDatabase;
        for (String pathParts: path) {
            ref = ref.child(pathParts);
        }
        ref.setValue(value);
    }
}

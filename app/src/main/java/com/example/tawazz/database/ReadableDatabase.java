package com.example.tawazz.database;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class ReadableDatabase {
    private FirebaseFirestore mFirebaseFirestore;

    public ReadableDatabase(FirebaseFirestore mFirebaseFirestore) {
        this.mFirebaseFirestore = mFirebaseFirestore;
    }

    public CollectionReference getCollection(String collectionName) {
        return mFirebaseFirestore.collection(collectionName);
    }

    public void add(String collectionTarget, Object data) {
        mFirebaseFirestore.collection(collectionTarget).add(data);
    }

    public void handleAllThatEqualTo(String collectionName, final String field, Object equalTo, final DocHandler handler) {
        mFirebaseFirestore.collection(collectionName).whereEqualTo(field, equalTo).get().addOnCompleteListener(
            new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                            handler.handle(documentSnapshot.getData());
                        }
                    }
                }
            });
    }

}

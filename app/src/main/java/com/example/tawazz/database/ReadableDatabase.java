package com.example.tawazz.database;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.tawazz.utils.condition.Condition;
import com.example.tawazz.utils.condition.ConditionalWaiter;
import com.example.tawazz.utils.exceptions.FailedWaitingForConditionException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.concurrent.atomic.AtomicBoolean;

public class ReadableDatabase {
    private FirebaseFirestore mFirebaseFirestore;

    public ReadableDatabase(FirebaseFirestore firebaseFirestore) {
        this.mFirebaseFirestore = firebaseFirestore;
    }

    public CollectionReference  getCollection(String collectionName) {
        return mFirebaseFirestore.collection(collectionName);
    }

    public void add(String collectionTarget, Object data) {
        mFirebaseFirestore.collection(collectionTarget).add(data);
    }

    public void handleAllThatEqualTo(String collectionName, final String field, Object equalTo, final DocHandler handler) {

        final AtomicBoolean isEnd = new AtomicBoolean(false);
        final Task task = mFirebaseFirestore.collection(collectionName).whereEqualTo(field, equalTo).get().addOnCompleteListener(
            new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    Log.e("TAWAZZ", "----------------------------------------------------------------------");
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                            handler.handle(documentSnapshot.getData());
                        }
                    }
//                    isEnd.set(true);
                }
            });

//        try {
//            new ConditionalWaiter(new Condition() {
//                @Override
//                public boolean isConfirmed() {
//                    Log.e("TAWAZZ", ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//
//                    return isEnd.get();
//                }
//            }).waitTill();
//        } catch (FailedWaitingForConditionException e) {
//            e.printStackTrace();
//        }
    }

}

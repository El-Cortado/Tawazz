package com.example.tawazz.database;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;

public class ReadableDatabase {
    private Firestore mFirebaseFirestore;

    public ReadableDatabase(Firestore firebaseFirestore) {
        this.mFirebaseFirestore = firebaseFirestore;
    }

    public CollectionReference getCollection(String collectionName) {
        return mFirebaseFirestore.collection(collectionName);
    }

    public void add(String collectionTarget, Object data) {
        mFirebaseFirestore.collection(collectionTarget).add(data);
    }

    public void handleAllThatEqualTo(String collectionName, final String field, Object equalTo, final DocHandler handler) {
        ApiFuture<QuerySnapshot> querySnapshot = mFirebaseFirestore.collection(collectionName).whereEqualTo(field, equalTo).get();
        try {
            for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
                handler.handle(document.getData());
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



//        .addOnCompleteListener(
//            new OnCompleteListener<QuerySnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                    Log.e("TAWAZZ", "----------------------------------------------------------------------");
//                    if (task.isSuccessful()) {
//                        for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
//                            handler.handle(documentSnapshot.getData());
//                        }
//                    }
//                    isEnd.set(true);
//                }
//            });

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
//    }

}

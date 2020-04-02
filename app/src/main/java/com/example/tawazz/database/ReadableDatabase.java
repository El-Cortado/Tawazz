package com.example.tawazz.database;

import androidx.annotation.NonNull;

import com.example.tawazz.database.exceptions.FailedGettingAllDocsException;
import com.example.tawazz.task.TaskCompletedWaiterFactory;
import com.example.tawazz.utils.FailedWaitingForCondition;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public List<Map> getAllThatEqualTo(String collectionName, String field, Object equalTo) throws FailedGettingAllDocsException {
        final List<Map> docs = new ArrayList<>();
        try {
            Task task = mFirebaseFirestore.collection(collectionName).whereEqualTo(field, equalTo).get().addOnCompleteListener(
                new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                docs.add(documentSnapshot.getData());
                            }
                        }
                    }
                }
        );
            new TaskCompletedWaiterFactory().create(task).waitTill();
        } catch (FailedWaitingForCondition e) {
            throw new FailedGettingAllDocsException(e);
        }

        return docs;
    }

    public void getCollection(String collectionTarget, Object data) {

    }
}

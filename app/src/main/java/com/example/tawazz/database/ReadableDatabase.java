package com.example.tawazz.database;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;

import java.util.concurrent.ExecutionException;

public class ReadableDatabase {
    private Firestore mFirebaseFirestore;

    public ReadableDatabase(Firestore firebaseFirestore) {
        this.mFirebaseFirestore = firebaseFirestore;
    }

    public CollectionReference getCollection(String collectionName) {
        return mFirebaseFirestore.collection(collectionName);
    }

    public DocumentReference add(String collectionTarget, Object data) throws AddingToFirestoreException {
        try {
            return mFirebaseFirestore.collection(collectionTarget).add(data).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            throw new AddingToFirestoreException(e);
        }
    }

    public void handleAllThatEqualTo(String collectionName, final String field, Object equalTo, final DocHandler handler) {
        ApiFuture<QuerySnapshot> querySnapshot = mFirebaseFirestore.collection(collectionName).whereEqualTo(field, equalTo).get();
        try {
            for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
                handler.handle(document.getData());
            }
        } catch (ExecutionException | InterruptedException e) {
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

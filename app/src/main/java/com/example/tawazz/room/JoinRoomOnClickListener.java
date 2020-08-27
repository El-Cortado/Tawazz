package com.example.tawazz.room;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.tawazz.consts.Constants;
import com.example.tawazz.database.ReadableDatabase;
import com.example.tawazz.utils.Notifier;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

public class JoinRoomOnClickListener implements View.OnClickListener {

    private final ReadableDatabase mReadableDatabase;
    private final Notifier<Boolean> mJoinRoomRequestNotifier;
    private final UUID uuid;

    public JoinRoomOnClickListener(ReadableDatabase readableDatabase, Notifier<Boolean> joinRoomRequestNotifier, UUID uuid) {
        this.mReadableDatabase = readableDatabase;
        mJoinRoomRequestNotifier = joinRoomRequestNotifier;
        this.uuid = uuid;
    }

    @Override
    public void onClick(View view) {
        Log.e("TAWAZZ", ">>>>>>>>>>>>>>>>>>>>>>>>> 1 >>>>>>>>>>");

        final AtomicBoolean res = new AtomicBoolean(false);
        final AtomicBoolean end = new AtomicBoolean(false);
        Task<QuerySnapshot> querySnapshotTask = mReadableDatabase.getCollection(Constants.ROOMS_DATABASE_KEY).whereEqualTo(Constants.ROOM_ID_READABLE_DATABASE_KEY, uuid.toString()).get();

        final Task task = querySnapshotTask.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                Log.e("TAWAZZ", ">>>>>>>>>>>>>>>>>>>>>>>>> 1 >>>>>>>>>> dhfhgsjdhfsjdmbfsmdjfhsdmjhfs");

                if (task.isSuccessful()) {
//                    QuerySnapshot result = task.getResult();
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                        Log.e("TAWAZZ", ">>>>>>>>>>>>>>>>>>>>>>>>> 1 >>>>>>>>>> dhfhgsjdhfsjdmbfsmdjfhsdmjhfsdhfhgsjdhfsjdmbfsmdjfhsdmjhfsdhfhgsjdhfsjdmbfsmdjfhsdmjhfsdhfhgsjdhfsjdmbfsmdjfhsdmjhfs");
//                        mJoinRoomRequestNotifier.notify(true);
                    }
                }
//                mJoinRoomRequestNotifier.notify(false);
            }
        });

        task.
        Log.e("TAWAZZ", "AFTERAFTERAFTERAFTERAFTERAFTERAFTERAFTER");

//        queryDocumentSnapshots.getDocuments();
    }
}

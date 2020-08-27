package com.example.tawazz.room;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;

import com.example.tawazz.consts.Constants;
import com.example.tawazz.database.ReadableDatabase;
import com.example.tawazz.databinding.JoinRoomBinding;
import com.example.tawazz.utils.Notifier;
import com.example.tawazz.utils.condition.Condition;
import com.example.tawazz.utils.condition.ConditionalWaiter;
import com.example.tawazz.utils.exceptions.FailedWaitingForConditionException;
import com.example.tawazz.utils.exceptions.SupplyingException;
import com.example.tawazz.utils.supplier.ThrowingSupplier;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

public class JoinRoomToRaffleRoomSupplier implements ThrowingSupplier<NavDirections> {
    private JoinRoomBinding mJoinRoomBinding;

    public JoinRoomToRaffleRoomSupplier(JoinRoomBinding mJoinRoomBinding) {
        this.mJoinRoomBinding = mJoinRoomBinding;
    }

    @Override
    public NavDirections supply() throws SupplyingException {
        try {
            UUID uuid = UUID.fromString(mJoinRoomBinding.roomIdInput.getText().toString());

            final AtomicBoolean flag = new AtomicBoolean(false);


//            , Constants.ROOM_ID_READABLE_DATABASE_KEY, uuid.toString(), new DocHandler() {
//                @Override
//                public void handle(Map roomDoc) {
//                    flag.set(true);
//                }
//            });

            return JoinRoomDirections.actionJoinRoomToRaffleRoom(uuid.toString());

        } catch (Exception e) {
            Log.e("TAWAZZ", ">>>>>>>>>>>>>>>>>>>> ERRRORR >>>>>>>>>>", e);
            throw new SupplyingException(e, "Invalid Room UUID");
        }
    }


////            try {
//        try {
//            new ConditionalWaiter(new Condition() {
//                @Override
//                public boolean isConfirmed() {
//                    return end.get();
//                }
//            }).waitTill(40000);
//        } catch (FailedWaitingForConditionException e) {
//            e.printStackTrace();
//        }
//
//        new Runnable() {
//
//            @Override
//            public void run() {
//                while (!task.isComplete()) {
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }.start();
////                task.getResult();
//            } catch (FailedWaitingForConditionException e) {
//                e.printStackTrace();
//            }

//        try {
//            querySnapshotTask.wait();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        if (res.get()) {
//            Log.e("TAWAZZ", ">>>>>>>>>>>>>>>>>>>>>>>>> 2 >>>>>>>>>>");
//            return true;
//        }
//        Log.e("TAWAZZ", ">>>>>>>>>>>>>>>>>>>>>>>>> 2 >>>>>>>>>>2 >>>>>>>>>>2 >>>>>>>>>>2 >>>>>>>>>>2 >>>>>>>>>>");

//        for (QueryDocumentSnapshot documentSnapshot : querySnapshotTask.getResult()) {
//            Log.e("TAWAZZ", ">>>>>>>>>>>>>>>>>>>>>>>>> 3 >>>>>>>>>>");
//
//            String roomId = (String)documentSnapshot.getData().get(Constants.ROOM_ID_READABLE_DATABASE_KEY);
//            if (roomId.equals(uuid.toString())) {
//                return true;
//            }
//        }
//        return false;
//    }

}

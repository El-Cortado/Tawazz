package com.example.tawazz.room;

import androidx.navigation.NavDirections;

import com.example.tawazz.databinding.JoinRoomBinding;
import com.example.tawazz.utils.Supplier;

public class JoinRoomToRaffleRoomSupplier implements Supplier<NavDirections> {
    private JoinRoomBinding mJoinRoomBinding;

    public JoinRoomToRaffleRoomSupplier(JoinRoomBinding mJoinRoomBinding) {
        this.mJoinRoomBinding = mJoinRoomBinding;
    }

    @Override
    public NavDirections supply() {
        return JoinRoomDirections.actionJoinRoomToRaffleRoom(mJoinRoomBinding.roomIdInput.getText().toString());
    }
}

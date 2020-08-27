package com.example.tawazz.room;

import androidx.navigation.NavDirections;

import com.example.tawazz.database.ReadableDatabase;
import com.example.tawazz.databinding.JoinRoomBinding;
import com.example.tawazz.utils.ThrowingSupplier;
import com.example.tawazz.utils.exceptions.SupplyingException;

import java.util.UUID;

public class JoinRoomToRaffleRoomSupplier implements ThrowingSupplier<NavDirections> {
    private JoinRoomBinding mJoinRoomBinding;
    private final ReadableDatabase readableDatabase;

    public JoinRoomToRaffleRoomSupplier(JoinRoomBinding mJoinRoomBinding, ReadableDatabase readableDatabase) {
        this.mJoinRoomBinding = mJoinRoomBinding;
        this.readableDatabase = readableDatabase;
    }

    @Override
    public NavDirections supply() throws SupplyingException {
        try {
            UUID uuid = UUID.fromString(mJoinRoomBinding.roomIdInput.getText().toString());

            return JoinRoomDirections.actionJoinRoomToRaffleRoom(uuid.toString());

        } catch (Exception e) {
            throw new SupplyingException(e);
        }
    }
}

package com.example.tawazz.room;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.tawazz.database.Database;
import com.example.tawazz.database.ReadableDatabase;
import com.example.tawazz.databinding.JoinRoomBinding;
import com.example.tawazz.utils.Notifier;
import com.example.tawazz.utils.gui.NavigationOnClickListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.UUID;

public class JoinRoom extends Fragment {

    private JoinRoomBinding mBinding;
    public JoinRoom() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = JoinRoomBinding.inflate(getLayoutInflater(), container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        UUID roomId = UUID.randomUUID(); // get from textbox

        Button buttonJoinGroup = mBinding.joinButton;

        NavController navController = Navigation.findNavController(view);

        ReadableDatabase readableDatabase = new ReadableDatabase(FirebaseFirestore.getInstance());
        Notifier<Boolean> notifier = new Notifier<>();

        JoinRoomToRaffleRoomSupplier joinRoomToRaffleRoomSupplier = new JoinRoomToRaffleRoomSupplier(mBinding);
        notifier.register(new JoinRoomRequestObserver(view, new NavigationOnClickListener(navController, joinRoomToRaffleRoomSupplier)));

        buttonJoinGroup.setOnClickListener(new JoinRoomOnClickListener(readableDatabase, notifier, roomId));
    }
}

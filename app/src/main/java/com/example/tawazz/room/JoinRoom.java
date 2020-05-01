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

import com.example.tawazz.R;
import com.example.tawazz.utils.gui.NavigationOnClickListener;

import java.util.UUID;

public class JoinRoom extends Fragment {
    public JoinRoom() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.join_room, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        UUID roomId = UUID.randomUUID(); // get from textbox

        Button buttonJoinGroup = view.findViewById(R.id.joinButton);

        NavController navController = Navigation.findNavController(view);
        buttonJoinGroup.setOnClickListener(
                new NavigationOnClickListener(navController, JoinRoomDirections.actionJoinRoomToRaffleRoom(roomId.toString())));

    }
}

package com.example.tawazz.room;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.tawazz.R;
import com.example.tawazz.utils.gui.NavigationOnClickListener;

import java.util.UUID;

public class NewRoomCreation extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.new_room_creation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        UUID roomId = UUID.randomUUID();

        TextView textView = view.findViewById(R.id.room_id);
        textView.setText(roomId.toString());

        Button buttonCreate = view.findViewById(R.id.createButton);

        NavController navController = Navigation.findNavController(view);
        NewRoomCreationDirections.ActionNewRoomCreationToRaffleRoom createNewRoomAction = NewRoomCreationDirections.actionNewRoomCreationToRaffleRoom(roomId.toString());
        buttonCreate.setOnClickListener(
                new NavigationOnClickListener(navController, createNewRoomAction));

    }
}

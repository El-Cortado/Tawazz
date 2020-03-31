package com.example.tawazz.menu;

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

public class MenuFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Button buttonJoinGroup = view.findViewById(R.id.joinRoomButton);

        NavController navController = Navigation.findNavController(view);
        buttonJoinGroup.setOnClickListener(
                new NavigationOnClickListener(navController, R.id.action_menuFragment_to_joinRoom));

        Button buttonCreateRoom = view.findViewById(R.id.createRoomButton);
        buttonCreateRoom.setOnClickListener(
                new NavigationOnClickListener(navController, R.id.action_menuFragment_to_newRoomCreation));
    }
}

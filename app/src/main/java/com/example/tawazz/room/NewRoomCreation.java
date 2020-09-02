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

import com.example.tawazz.database.ReadableDatabaseSingleton;
import com.example.tawazz.databinding.NewRoomCreationBinding;
import com.example.tawazz.utils.gui.NavigationOnClickListener;

import java.util.UUID;

public class NewRoomCreation extends Fragment {

    private NewRoomCreationBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = NewRoomCreationBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        UUID roomId = UUID.randomUUID();

        TextView textView = mBinding.roomId;
        textView.setText(roomId.toString());

        Button buttonCreate = mBinding.createButton;

        NavController navController = Navigation.findNavController(view);
        NewRoomCreationDirections.ActionNewRoomCreationToRaffleRoom createNewRoomAction = NewRoomCreationDirections.actionNewRoomCreationToRaffleRoom(roomId.toString());

        buttonCreate.setOnClickListener(new NewRoomCreationOnClickListener(ReadableDatabaseSingleton.getInstance(),
                new NavigationOnClickListener(navController, createNewRoomAction), roomId));

    }
}

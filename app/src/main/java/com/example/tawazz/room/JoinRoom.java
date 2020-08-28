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

import com.example.tawazz.database.ReadableDatabase;
import com.example.tawazz.database.ReadableDatabaseSingleton;
import com.example.tawazz.databinding.JoinRoomBinding;
import com.example.tawazz.utils.gui.NavigationOnClickListener;

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
        Button buttonJoinGroup = mBinding.joinButton;

        NavController navController = Navigation.findNavController(view);

        ReadableDatabase readableDatabase = ReadableDatabaseSingleton.getInstance();
        JoinRoomToRaffleRoomSupplier joinRoomToRaffleRoomSupplier = new JoinRoomToRaffleRoomSupplier(mBinding, readableDatabase);

        buttonJoinGroup.setOnClickListener(new NavigationOnClickListener(navController, joinRoomToRaffleRoomSupplier));
    }
}

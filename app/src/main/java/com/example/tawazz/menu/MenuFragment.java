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

import com.example.tawazz.databinding.MenuBinding;
import com.example.tawazz.utils.gui.NavigationOnClickListener;

public class MenuFragment extends Fragment {
    private MenuBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = MenuBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Button buttonJoinGroup = mBinding.joinRoomButton;

        NavController navController = Navigation.findNavController(view);
        buttonJoinGroup.setOnClickListener(
                new NavigationOnClickListener(navController, MenuFragmentDirections.actionMenuFragmentToJoinRoom()));

        Button buttonCreateRoom = mBinding.createRoomButton;
        buttonCreateRoom.setOnClickListener(
                new NavigationOnClickListener(navController, MenuFragmentDirections.actionMenuFragmentToNewRoomCreation()));
    }
}

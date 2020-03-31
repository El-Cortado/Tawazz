package com.example.tawazz.room;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tawazz.R;
import com.example.tawazz.client.User;
import com.example.tawazz.consts.Constants;
import com.example.tawazz.database.Database;
import com.example.tawazz.icon.Icon;
import com.example.tawazz.icon.IconRepository;
import com.example.tawazz.icon.exceptions.FailedUpdateUserIconException;
import com.example.tawazz.storage.Storage;
import com.example.tawazz.storage.StorageSingleton;
import com.example.tawazz.touch.TouchListener;
import com.example.tawazz.touch.TouchStatus;
import com.example.tawazz.touch.TouchStatusObserver;
import com.example.tawazz.utils.Notifier;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class RaffleRoom extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.raffle_room, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        try {
            View userIconLayout = view.findViewById(R.id.user_icon_layout);
            ImageView userIconImage = userIconLayout.findViewById(R.id.albert_einstein);

            // todo: all the user uuid will be saved in the db
            UUID groupUuid = UUID.randomUUID();

            // todo: getting a picture from the user
            Uri iconUri = Uri.parse("android.resource://com.example.tawazz/drawable/albert_einstein");
            User user = new User(groupUuid, new Icon(iconUri));
            user.generateId();
            Notifier<TouchStatus> statusNotifier = new Notifier<>();

            DatabaseReference databaseRef;
            databaseRef = FirebaseDatabase.getInstance().getReference();
            Database database = new Database(databaseRef);
            database.addUser(user);

            Storage storage = StorageSingleton.getInstance(getContext());
            IconRepository iconRepository = new IconRepository(storage);
            iconRepository.addUserIcon(user);

            statusNotifier.register(new TouchStatusObserver(database, user));
            userIconLayout.setOnTouchListener(new TouchListener(userIconImage, statusNotifier));

        } catch (FailedUpdateUserIconException e) {
            Log.e(Constants.TAWAZZ_LOG_TAG, "Failed Init Application", e);
        }
    }
}

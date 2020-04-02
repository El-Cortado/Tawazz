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
import com.example.tawazz.consts.Constants;
import com.example.tawazz.database.Database;
import com.example.tawazz.database.ReadableDatabase;
import com.example.tawazz.database.exceptions.FailedGettingAllDocsException;
import com.example.tawazz.icon.Icon;
import com.example.tawazz.icon.IconRepository;
import com.example.tawazz.icon.exceptions.FailedUpdateUserIconException;
import com.example.tawazz.storage.Storage;
import com.example.tawazz.storage.StorageSingleton;
import com.example.tawazz.task.TaskCompletedWaiterFactory;
import com.example.tawazz.touch.TouchListener;
import com.example.tawazz.touch.TouchStatus;
import com.example.tawazz.touch.TouchStatusObserver;
import com.example.tawazz.touch.TouchUpdater;
import com.example.tawazz.user.NewUserHandler;
import com.example.tawazz.user.NewUserListener;
import com.example.tawazz.user.SignedUsersSingleton;
import com.example.tawazz.user.User;
import com.example.tawazz.user.UserRepository;
import com.example.tawazz.user.exceptions.UserAlreadyExistException;
import com.example.tawazz.utils.Notifier;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
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
            UUID roomUuid = UUID.randomUUID();

            // todo: getting a picture from the user (now this is a static pic)
            Uri iconUri = Uri.parse("android.resource://com.example.tawazz/drawable/albert_einstein");
            User user = new User(roomUuid, new Icon(iconUri));
            user.generateId();
            Notifier<TouchStatus> statusNotifier = new Notifier<>();

            DatabaseReference databaseRef;
            databaseRef = FirebaseDatabase.getInstance().getReference();
            Database database = new Database(databaseRef);

            Storage storage = StorageSingleton.getInstance(getContext());
            IconRepository iconRepository = new IconRepository(storage, new TaskCompletedWaiterFactory());
            iconRepository.addUserIcon(user);

            NewUserHandler newUserHandler = new NewUserHandler(
                    new IconRepository(storage, new TaskCompletedWaiterFactory()),
                    SignedUsersSingleton.getInstance(),
                    user
                    );

            databaseRef.child(Constants.ROOMS_DATABASE_KEY).
                    child(roomUuid.toString()).
                    child(Constants.USERS_DATABASE_KEY).getRef().addChildEventListener(
                    new NewUserListener(newUserHandler)
            );

            ReadableDatabase readableDatabase = new ReadableDatabase(FirebaseFirestore.getInstance());
            UserRepository userRepository = new UserRepository(new HashMap<UUID, User>(), database, readableDatabase);

            initAlreadySignedUsers(roomUuid, userRepository, newUserHandler);

            userRepository.add(user);

            TouchUpdater touchUpdater = new TouchUpdater(userRepository);
            statusNotifier.register(new TouchStatusObserver(user, touchUpdater));
            userIconLayout.setOnTouchListener(new TouchListener(userIconImage, statusNotifier));


        } catch (UserAlreadyExistException | FailedGettingAllDocsException | FailedUpdateUserIconException e) {
            Log.e(Constants.TAWAZZ_LOG_TAG, "Failed Init Application", e);
        }
    }

    private void initAlreadySignedUsers(
            UUID roomId, UserRepository userRepository, NewUserHandler newUserHandler) throws FailedGettingAllDocsException {
        List<UUID> signedUsersIds = userRepository.getAllRoomUsers(roomId);

        for (UUID userId : signedUsersIds) {
            newUserHandler.handle(userId);
        }
    }
}

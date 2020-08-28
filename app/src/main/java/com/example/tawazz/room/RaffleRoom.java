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
import com.example.tawazz.database.ReadableDatabaseSingleton;
import com.example.tawazz.database.UsersDatabaseUtils;
import com.example.tawazz.databinding.RaffleRoomBinding;
import com.example.tawazz.icon.Icon;
import com.example.tawazz.icon.IconRepository;
import com.example.tawazz.icon.IconsDatabaseUtils;
import com.example.tawazz.icon.exceptions.FailedUpdateUserIconException;
import com.example.tawazz.icon.gui.RemoteIconBuilder;
import com.example.tawazz.storage.Storage;
import com.example.tawazz.storage.StorageSingleton;
import com.example.tawazz.touch.DatabaseTouchStatusConverter;
import com.example.tawazz.touch.TouchListener;
import com.example.tawazz.touch.TouchStatus;
import com.example.tawazz.touch.TouchStatusHandler;
import com.example.tawazz.touch.TouchStatusObserver;
import com.example.tawazz.touch.TouchUpdater;
import com.example.tawazz.touch.remote.RemoteTouchHandlerFactory;
import com.example.tawazz.touch.remote.RemoteTouchListener;
import com.example.tawazz.user.NewUserHandler;
import com.example.tawazz.user.NewUserListener;
import com.example.tawazz.user.SignedUsersSingleton;
import com.example.tawazz.user.User;
import com.example.tawazz.user.UserRepository;
import com.example.tawazz.utils.Notifier;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class RaffleRoom extends Fragment {

    private RaffleRoomBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = RaffleRoomBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        try {
            View userIconLayout = mBinding.userIconLayout;
            ImageView userIconImage = mBinding.albertEinstein;

            // todo: all the user uuid will be saved in the db
            String roomUuidString = RaffleRoomArgs.fromBundle(getArguments()).getRoomId();
//            String roomUuidString = "0633c074-a68a-4cdd-9864-f9b1e761e304";
            UUID roomUuid = UUID.fromString(roomUuidString);

            // todo: getting a picture from the user (now this is a static pic)
            Uri iconUri = Uri.parse("android.resource://com.example.tawazz/drawable/albert_einstein");
            User user = new User(roomUuid, new Icon(iconUri));
            user.generateId();
            Notifier<TouchStatus> statusNotifier = new Notifier<>();

            DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();
            Database database = new Database(databaseRef);

            Storage storage = StorageSingleton.getInstance();
            IconsDatabaseUtils iconsDatabaseUtils = new IconsDatabaseUtils();
            IconRepository iconRepository = new IconRepository(iconsDatabaseUtils, storage);
            iconRepository.addUserIcon(user);

            UsersDatabaseUtils usersDatabaseUtils = new UsersDatabaseUtils();
            DatabaseTouchStatusConverter databaseTouchStatusConverter = new DatabaseTouchStatusConverter();

            NewUserHandler newUserHandler = new NewUserHandler(
                    SignedUsersSingleton.getInstance(),
                    user,
                    new RemoteTouchListener(database, databaseTouchStatusConverter, usersDatabaseUtils),
                    new RemoteTouchHandlerFactory(
                            new RemoteIconBuilder(getContext(), mBinding, storage, iconsDatabaseUtils)));

            databaseRef.child(Constants.ROOMS_DATABASE_KEY).
                    child(roomUuid.toString()).
                    child(Constants.USERS_DATABASE_KEY).getRef().addChildEventListener(
                    new NewUserListener(newUserHandler)
            );

            UserRepository userRepository = new UserRepository(database, ReadableDatabaseSingleton.getInstance(), newUserHandler, usersDatabaseUtils);

            initAlreadySignedUsers(roomUuid, userRepository);

            userRepository.add(user);

            TouchUpdater touchUpdater = new TouchUpdater(userRepository);
            statusNotifier.register(new TouchStatusObserver(user, touchUpdater));
            userIconImage.setImageResource(R.drawable.albert_einstein);
            userIconImage.setVisibility(View.GONE);

            TouchStatusHandler touchListenerHandler = new TouchStatusHandler(userIconImage, statusNotifier);
            userIconLayout.setOnTouchListener(new TouchListener(touchListenerHandler));


        } catch (FailedUpdateUserIconException e) {
            Log.e(Constants.TAWAZZ_LOG_TAG, "Failed Init Application", e);
        }
    }

    private void initAlreadySignedUsers(
            UUID roomId, UserRepository userRepository) {
        userRepository.initAllRoomUsers(roomId);
    }
}

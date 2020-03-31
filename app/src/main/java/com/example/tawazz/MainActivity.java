package com.example.tawazz;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import com.example.tawazz.utils.gui.FragmentLoader;

public class MainActivity extends AppCompatActivity {
    private static String DEBUG_TAG = "TAWWAZZ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        try {
            setContentView(R.layout.activity_main);
//            setContentView(R.layout.user_icon);
//
//            View userIconLayout = findViewById(R.id.user_icon_layout);
//            ImageView userIconImage = userIconLayout.findViewById(R.id.albert_einstein);
//
//            // todo: all the user uuid will be saved in the db
//            UUID groupUuid = UUID.randomUUID();
//
//            // todo: getting a picture from the user
//            Uri iconUri = Uri.parse("android.resource://com.example.tawazz/drawable/albert_einstein");
//            User user = new User(groupUuid, new Icon(iconUri));
//            user.generateId();
//            Notifier<TouchStatus> statusNotifier = new Notifier<>();
//
//            DatabaseReference databaseRef;
//            databaseRef = FirebaseDatabase.getInstance().getReference();
//            Database database = new Database(databaseRef);
//            database.addUser(user);
//
//            Storage storage = StorageSingleton.getInstance();
//            ServerCommunicator serverCommunicator = new ServerCommunicator(database, storage, user);
//            serverCommunicator.signInUser();
//
//            statusNotifier.register(new TouchStatusObserver(serverCommunicator));
//    //        userIconLayout.setOnTouchListener(new TouchListener(userIconImage, statusNotifier));

//            FragmentLoader fragmentLoader = new FragmentLoader(getSupportFragmentManager());
//            fragmentLoader.replaceFragment(
//                    R.id.container, new MenuFragment(Navigation.findNavController(
//                            this, R.id.container)));

//        } catch (FailedSignInUserException e) {
//            Log.e(Constants.TAWAZZ_LOG_TAG, "Failed Init Application", e);
//        }

    }
}

package com.example.tawazz.database;

import com.example.tawazz.R;
import com.example.tawazz.utils.ResourcesSingleton;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

//import com.google.cloud.firestore.v1.FirestoreClient;

public class ReadableDatabaseSingleton {

    private static ReadableDatabase sInstance;

    public static ReadableDatabase getInstance() {
        if (sInstance == null) {
            sInstance = create();
        }
        return sInstance;
    }

    private static ReadableDatabase create() {
//        GoogleCredentials credentials = null;
        try {
            InputStream googleServicesInputStream = ResourcesSingleton.getInstance().openRawResource(R.raw.google_services);
//            FirebaseOptions options = new FirebaseOptions.Builder()
//                    .setDatabaseUrl("https://tawazz-897a0.firebaseio.com")
//                    .build();
//
//            FirebaseApp.initializeApp(options);
//
            GoogleCredentials credentials = GoogleCredentials.fromStream(googleServicesInputStream);

            FirestoreOptions firestoreOptions =
                    FirestoreOptions.getDefaultInstance().toBuilder()
                            .setProjectId("tawazz-897a0")
//                            .setDatabaseId()
                            .setCredentials(credentials)
                            .build();
            Firestore db = firestoreOptions.getService();
            return new ReadableDatabase(db);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
//////            credentials = GoogleCredentials.getApplicationDefault();
//            FirebaseOptions.Builder builder = new FirebaseOptions.Builder();
//            FirebaseOptions options = builder.(credentials).setProjectId("tawazz").build();
////            FirebaseApp.initializeApp(options);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        Firestore db = new FirestoreOptions.DefaultFirestoreFactory().create();
//        try {
//            db = FirestoreClient.;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}

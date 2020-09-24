package com.example.tawazz;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tawazz.icon.Icon;
import com.example.tawazz.icon.MyIconSingleton;
import com.example.tawazz.utils.ResourcesSingleton;

import static com.example.tawazz.consts.Constants.SELECT_PICTURE;

public class MainActivity extends AppCompatActivity {

    private static MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainActivity = this;

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ResourcesSingleton.init(getApplicationContext());

        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // call the callback
    }

    public static MainActivity getInstance() {
        return mainActivity;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                MyIconSingleton.init(new Icon(selectedImageUri));
            }
        }
    }

    /**
     * helper to retrieve the path of an image URI
     */
    public String getPath(Uri uri) {
        // just some safety built in
        if( uri == null ) {
            // TODO perform some logging or show user feedback
            return null;
        }
        // try to retrieve the image from the media store first
        // this will only work for images selected from gallery
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if( cursor != null ){
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String path = cursor.getString(column_index);
            cursor.close();
            return path;
        }
        // this is our fallback here
        return uri.getPath();
    }

}

// todo: lock application from rotating
// todo: notify when application want to be destroyed
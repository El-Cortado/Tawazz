package com.example.tawazz.icon;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import com.example.tawazz.MainActivity;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import static com.example.tawazz.consts.Constants.SELECT_PICTURE;

public class ChooseIconHandler implements View.OnClickListener {
    @Override
    public void onClick(View view) {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        MainActivity.getInstance().startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    public String getRealPathFromURI(Uri contentURI, Activity context) {
        String[] projection = { MediaStore.Images.Media.DATA };
        @SuppressWarnings("deprecation")
        Cursor cursor = context.managedQuery(contentURI, projection, null,
                null, null);
        if (cursor == null) {
            Log.i("TAWAZZ", "null null null null null");

            return null;

        }
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        if (cursor.moveToFirst()) {
            String s = cursor.getString(column_index);
            // cursor.close();
            return s;
        }
        // cursor.close();
        Log.i("TAWAZZ", "null null null null null 2");

        return null;
    }

}

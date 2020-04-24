package com.example.tawazz.touch;

import android.graphics.Point;
import android.util.Log;

import com.example.tawazz.consts.Constants;
import com.example.tawazz.database.DatabaseDataConvertor;
import com.example.tawazz.utils.exceptions.FailedConvertingObjectException;
import com.google.firebase.database.DataSnapshot;

public class DatabaseTouchStatusConverter implements DatabaseDataConvertor {
    @Override
    public Object convert(DataSnapshot dataSnapshot) throws FailedConvertingObjectException {
        Iterable<DataSnapshot> userData = dataSnapshot.getChildren();
        Point touchLocation = new Point();
        TouchAction touchAction = null;
        for (DataSnapshot item : userData) {
            switch (item.getKey()) {
                case Constants.HEIGHT_DATABASE_KEY:
                    touchLocation.y = ((Long)item.getValue()).intValue();
                    Log.d(Constants.TAWAZZ_LOG_TAG, String.valueOf(touchLocation.y));
                    break;
                case Constants.WIDTH_DATABASE_KEY:
                    touchLocation.x = ((Long) item.getValue()).intValue();
                    Log.d(Constants.TAWAZZ_LOG_TAG, String.valueOf(touchLocation.x));

                    break;
                case Constants.TOUCH_ACTION_DATABASE_KEY:
                    touchAction = TouchAction.valueOf((String)item.getValue());
                    Log.d(Constants.TAWAZZ_LOG_TAG, touchAction.name());

                    break;
            }
        }

        if (touchAction == null) {
            throw new FailedConvertingObjectException();
        }
        return new TouchStatus(touchLocation, touchAction);
    }
}

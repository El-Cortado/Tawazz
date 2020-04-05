package com.example.tawazz.touch;

import android.graphics.Point;

import com.example.tawazz.consts.Constants;
import com.example.tawazz.database.DatabaseDataConvertor;
import com.google.firebase.database.DataSnapshot;

public class DatabaseTouchStatusConverter implements DatabaseDataConvertor {
    @Override
    public Object convert(DataSnapshot dataSnapshot) {
        Iterable<DataSnapshot> userData = dataSnapshot.getChildren();
        Point touchLocation = new Point();
        TouchAction touchAction = null;
        for (DataSnapshot item : userData) {
            switch (item.getKey()) {
                case Constants.HEIGHT_DATABASE_KEY:
                    touchLocation.y = (Integer) item.getValue();
                    break;
                case Constants.WIDTH_DATABASE_KEY:
                    touchLocation.x = (Integer) item.getValue();
                    break;
                case Constants.TOUCH_ACTION_DATABASE_KEY:
                    touchAction = TouchAction.valueOf(item.getKey());
                    break;
                default:
                    continue;
            }
        }

        return new TouchStatus(touchLocation, touchAction);
    }
}

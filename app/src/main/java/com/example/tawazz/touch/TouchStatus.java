package com.example.tawazz.touch;

import android.graphics.Point;

public class TouchStatus {
    private Point mLocation;
    private TouchAction mTouchAction;

    public TouchStatus(Point location, TouchAction touchAction) {
        this.mLocation = location;
        this.mTouchAction = touchAction;
    }

    public Point getLocation() {
        return mLocation;
    }

    public TouchAction getTouchAction() {
        return mTouchAction;
    }
}

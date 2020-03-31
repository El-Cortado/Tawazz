package com.example.tawazz.touch;

import android.graphics.Point;

public class TouchStatus {
    private Point mLocation;
    private TouchAction mTouchAction;

    public TouchStatus(Point location, TouchAction mTouchAction) {
        this.mTouchAction = mTouchAction;
        this.mLocation = location;
    }

    public Point getmLocation() {
        return mLocation;
    }

    public TouchAction getTouchAction() {
        return mTouchAction;
    }
}

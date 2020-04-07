package com.example.tawazz.touch;

import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;

public class TouchListener implements View.OnTouchListener {
    private  TouchStatusHandler mTouchStatusHandler;

    public TouchListener(TouchStatusHandler mTouchStatusHandler) {
        this.mTouchStatusHandler = mTouchStatusHandler;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getActionMasked();
        Point eventLocation = new Point((int)event.getX(), (int)event.getY());
        TouchAction touchAction;

        switch(action) {
            case (MotionEvent.ACTION_DOWN) :
                touchAction = TouchAction.DOWN;
                break;
            case (MotionEvent.ACTION_MOVE) :
                touchAction = TouchAction.MOVE;
                break;
            case (MotionEvent.ACTION_UP) :
                touchAction = TouchAction.UP;
                break;
            default :
                return v.onTouchEvent(event);
        }
        mTouchStatusHandler.handle(new TouchStatus(eventLocation, touchAction));
        return true;
    }
}

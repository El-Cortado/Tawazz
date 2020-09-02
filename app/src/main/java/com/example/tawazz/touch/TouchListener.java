package com.example.tawazz.touch;

import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;

public class TouchListener implements View.OnTouchListener {
    private  TouchStatusHandler mTouchStatusHandler;

    public TouchListener(TouchStatusHandler touchStatusHandler) {
        this.mTouchStatusHandler = touchStatusHandler;
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
        mTouchStatusHandler.handle(new TouchStatus(getUpdatedImageLocation(eventLocation), touchAction));
        return true;
    }

    private Point getUpdatedImageLocation(Point point) {
        int width = point.x - 200;
        int height = point.y - 200;

        return new Point(width, height);
    }

}

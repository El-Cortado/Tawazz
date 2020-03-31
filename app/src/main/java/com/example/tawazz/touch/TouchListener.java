package com.example.tawazz.touch;

import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.tawazz.R;
import com.example.tawazz.utils.Notifier;

public class TouchListener implements View.OnTouchListener {
    private ImageView mImageView;
    private Notifier<TouchStatus> mNotifierTouchStatus;

    public TouchListener(ImageView mImageView, Notifier<TouchStatus> notifierTouchStatus) {
        this.mImageView = mImageView;
        this.mNotifierTouchStatus = notifierTouchStatus;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getActionMasked();
        Point updatedPoint;

        switch(action) {
            case (MotionEvent.ACTION_DOWN) :
                mImageView.setVisibility(View.VISIBLE);
                updatedPoint = updateImageLocation(event);
                mImageView.setImageResource(R.drawable.albert_einstein);

                mNotifierTouchStatus.notify(
                        new TouchStatus(updatedPoint, TouchAction.DOWN));
                return true;

            case (MotionEvent.ACTION_MOVE) :
                updatedPoint = updateImageLocation(event);
                mImageView.setImageResource(R.drawable.albert_einstein);

                mNotifierTouchStatus.notify(
                        new TouchStatus(updatedPoint, TouchAction.MOVE));

                return true;
            case (MotionEvent.ACTION_UP) :
                mImageView.setVisibility(View.GONE);
                updatedPoint = updateImageLocation(event);

                mNotifierTouchStatus.notify(
                        new TouchStatus(updatedPoint, TouchAction.UP));
                return true;
            default :
                return v.onTouchEvent(event);
        }
    }

    private Point updateImageLocation(MotionEvent event) {
        int width = (int)event.getX() - 200;
        int height = (int)event.getY() - 200;
        mImageView.setX(width);
        mImageView.setY(height);

        return new Point(width, height);
    }

}

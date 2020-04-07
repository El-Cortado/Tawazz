package com.example.tawazz.touch;

import android.graphics.Point;
import android.view.View;
import android.widget.ImageView;

import com.example.tawazz.R;
import com.example.tawazz.utils.Handler;
import com.example.tawazz.utils.Notifier;

public class TouchStatusHandler implements Handler<TouchStatus> {
    private ImageView mImageView;
    private Notifier<TouchStatus> mNotifierTouchStatus;

    public TouchStatusHandler(ImageView mImageView, Notifier<TouchStatus> mNotifierTouchStatus) {
        this.mImageView = mImageView;
        this.mNotifierTouchStatus = mNotifierTouchStatus;
    }

    public TouchStatusHandler(ImageView mImageView) {
        this.mImageView = mImageView;
        this.mNotifierTouchStatus = new Notifier<>();
    }

    public void handle(TouchStatus touchStatus) {
        Point updatedPoint;

        switch (touchStatus.getTouchAction()) {
            case UP:
                mImageView.setVisibility(View.GONE);
                updatedPoint = updateImageLocation(touchStatus.getmLocation());

                mNotifierTouchStatus.notify(
                        new TouchStatus(updatedPoint, TouchAction.UP));
                break;
            case DOWN:
                mImageView.setVisibility(View.VISIBLE);
                updatedPoint = updateImageLocation(touchStatus.getmLocation());
                mImageView.setImageResource(R.drawable.albert_einstein);

                mNotifierTouchStatus.notify(
                        new TouchStatus(updatedPoint, TouchAction.DOWN));

                break;
            case MOVE:
                updatedPoint = updateImageLocation(touchStatus.getmLocation());
                mImageView.setImageResource(R.drawable.albert_einstein);

                mNotifierTouchStatus.notify(
                        new TouchStatus(updatedPoint, TouchAction.MOVE));
                break;
        }

    }

    private Point updateImageLocation(Point point) {
        int width = point.x - 200;
        int height = point.y - 200;
        mImageView.setX(width);
        mImageView.setY(height);

        return new Point(width, height);
    }

}

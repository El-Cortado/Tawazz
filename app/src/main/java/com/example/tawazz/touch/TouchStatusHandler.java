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

    public TouchStatusHandler(ImageView imageView) {
        this.mImageView = imageView;
        this.mNotifierTouchStatus = new Notifier<>();
    }

    public void handle(TouchStatus touchStatus) {

        switch (touchStatus.getTouchAction()) {
            case UP:
                mImageView.setVisibility(View.GONE);
                updateImageLocation(touchStatus.getmLocation());

                mNotifierTouchStatus.notify(
                        new TouchStatus(touchStatus.getmLocation(), TouchAction.UP));
                break;
            case DOWN:
                mImageView.setVisibility(View.VISIBLE);
                updateImageLocation(touchStatus.getmLocation());

                mNotifierTouchStatus.notify(
                        new TouchStatus(touchStatus.getmLocation(), TouchAction.DOWN));

                break;
            case MOVE:
                mImageView.setVisibility(View.VISIBLE);
                updateImageLocation(touchStatus.getmLocation());

                mNotifierTouchStatus.notify(
                        new TouchStatus(touchStatus.getmLocation(), TouchAction.MOVE));
                break;
        }

    }

    private void updateImageLocation(Point point) {
        mImageView.setX(point.x);
        mImageView.setY(point.y);
    }

}

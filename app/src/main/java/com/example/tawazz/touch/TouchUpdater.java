package com.example.tawazz.touch;

import android.graphics.Point;

import com.example.tawazz.consts.Constants;
import com.example.tawazz.user.User;
import com.example.tawazz.user.UserRepository;

public class TouchUpdater {
    private UserRepository mUserRepository;

    public TouchUpdater(UserRepository userRepository) {
        this.mUserRepository = userRepository;
    }

    public void updateTouchLocation(Point newLocation, User user) {
        mUserRepository.updateUserField(user, Constants.WIDTH_DATABASE_KEY, newLocation.x);
        mUserRepository.updateUserField(user, Constants.HEIGHT_DATABASE_KEY, newLocation.y);
    }

    public void updateTouchAction(TouchAction touchAction, User user) {
        mUserRepository.updateUserField(user, Constants.TOUCH_ACTION_DATABASE_KEY, touchAction);
    }

}

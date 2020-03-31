package com.example.tawazz.utils.gui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.tawazz.R;
// unused!
public class BackPageFragment extends Fragment {
    private final Fragment mPreviousFragment;
    private final FragmentLoader mFragmentLoader;

    public BackPageFragment(Fragment mPreviousFragment, FragmentLoader mFragmentLoader) {
        this.mPreviousFragment = mPreviousFragment;
        this.mFragmentLoader = mFragmentLoader;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.back_page, container, false);
        Button backButton = view.findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentLoader.replaceFragment(R.id.container, mPreviousFragment);
            }
        });
        return view;
    }

    public Fragment getmPreviousFragment() {
        return mPreviousFragment;
    }
}

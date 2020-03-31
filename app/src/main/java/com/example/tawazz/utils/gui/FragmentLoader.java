package com.example.tawazz.utils.gui;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentLoader {
    private FragmentManager fragmentManager;

    public FragmentLoader(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void replaceFragment(int fragmentIdToReplace, Fragment fragmentDest) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(fragmentIdToReplace, fragmentDest);
        fragmentTransaction.commit();
    }

    public void addFragment(int addToFragment, Fragment fragmentToAdd) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(addToFragment, fragmentToAdd);
        fragmentTransaction.commit();
    }

}

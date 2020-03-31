package com.example.tawazz.utils;

import java.util.Observable;
import java.util.Observer;

public class Notifier<T> extends Observable {
    private Observable mObservable;

    public Notifier() {
    }

    public void notify(T status) {
        setChanged();
        notifyObservers(status);
    }

    public void register(Observer observer) {
        addObserver(observer);
    }
}

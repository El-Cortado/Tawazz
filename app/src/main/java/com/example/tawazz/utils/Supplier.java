package com.example.tawazz.utils;

public interface Supplier<T> extends ThrowingSupplier<T> {
    T supply();
}

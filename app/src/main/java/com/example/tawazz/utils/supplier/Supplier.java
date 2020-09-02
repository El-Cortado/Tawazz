package com.example.tawazz.utils.supplier;

public interface Supplier<T> extends ThrowingSupplier<T> {
    T supply();
}

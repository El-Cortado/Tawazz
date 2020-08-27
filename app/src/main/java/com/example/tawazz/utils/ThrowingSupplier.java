package com.example.tawazz.utils;

import com.example.tawazz.utils.exceptions.SupplyingException;

public interface ThrowingSupplier<T> {
    T supply() throws SupplyingException;
}

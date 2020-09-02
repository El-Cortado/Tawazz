package com.example.tawazz.utils.supplier;

import com.example.tawazz.utils.exceptions.SupplyingException;

public interface ThrowingSupplier<T> {
    T supply() throws SupplyingException;
}

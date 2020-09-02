package com.example.tawazz.utils;

import com.example.tawazz.utils.exceptions.FailedConvertingObjectException;

public interface Convertor<T, K> {
    K convert(T t) throws FailedConvertingObjectException;
}

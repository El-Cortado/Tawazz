package com.example.tawazz.utils.exceptions;

public class SupplyingException extends Exception {

    private String mMessage;

    public SupplyingException(Throwable cause, String message) {
        super(cause);

        mMessage = message;
    }

    public String getMessage() {
        return mMessage;
    }
}

package com.example.tawazz.download;

import androidx.annotation.NonNull;

public enum ExtensionType {
    JPG {
        @NonNull
        @Override
        public String toString() {
            return ".jpg";
        }
    }
}

package com.example.tawazz.download;

public enum ExtensionType {
    JPG {
        @Override
        public String getExtensionName() {
            return ".jpg";
        }
    };

    public abstract String getExtensionName();
}

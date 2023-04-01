package com.javafxstockchart.model.api;

/**
 * Output Size = size of request from TwelveData
 */
public enum OutputSize implements ApiConstructor{
    OUTPUT_SIZE(5000);

    private final int size;

    OutputSize(int size) {
        this.size = size;
    }

    @Override
    public String getValue() {
        return String.valueOf(size);
    }

    @Override
    public String getKey() {
        return "outputsize";
    }
}

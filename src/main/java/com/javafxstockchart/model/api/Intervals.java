package com.javafxstockchart.model.api;

/**
 * 1day, 1week, 1month intervals
 */
public enum Intervals implements ApiConstructor{
    ONE_DAY("1day"),
    ONE_WEEK("1week"),
    ONE_MONTH("1month");

    private final String interval;

    Intervals(String interval) {
        this.interval = interval;
    }

    @Override
    public String getValue() {
        return interval;
    }

    @Override
    public String getKey() {
        return "interval";
    }
}

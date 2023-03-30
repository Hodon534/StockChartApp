package com.javafxstockchart.model.api;

/**
 * 1min, 5min, 15min, 30min, 45min, 1h, 2h, 4h, 1day, 1week, 1month
 */
public enum Intervals implements ApiConstructor{
    ONE_MIN("1min"),
    FIVE_MIN("5min"),
    FIFTEEN_MIN("15min"),
    THIRTY_MIN("30min"),
    FORTY_FIVE_MIN("45min"),
    ONE_HOUR("1h"),
    TWO_HOURS("2h"),
    FOUR_HOURS("4h"),
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

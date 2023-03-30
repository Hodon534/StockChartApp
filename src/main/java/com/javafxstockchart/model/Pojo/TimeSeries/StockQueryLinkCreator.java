package com.javafxstockchart.model.Pojo.TimeSeries;

import com.javafxstockchart.model.api.ApiKey;
import com.javafxstockchart.model.api.Intervals;
import com.javafxstockchart.model.api.OutputSize;
import org.springframework.stereotype.Component;

/**
 * API Constructor - combining values to make a request and get data
 * Twelve Data API Documentation -> https://twelvedata.com/docs#
 * Demo link from site -> https://api.twelvedata.com/time_series?symbol=AAPL&interval=1min&apikey=demo&source=docs
 */

@Component
public class StockQueryLinkCreator {
    public static final String TWELVE_DATA_BASE_LINK = "https://api.twelvedata.com/";

    public String createLink(String interval, String symbol) {
        System.out.println(TWELVE_DATA_BASE_LINK +
                "time_series?" +
                setSymbol(symbol) +
                setInterval(interval) +
                setOutputSize() +
                setApiKey());
        return TWELVE_DATA_BASE_LINK +
                "time_series?" +
                setSymbol(symbol) +
                setInterval(interval) +
                setOutputSize() +
                setApiKey();
    }

    private String setInterval(String interval) {
        String output = "";
        switch (interval) {
            case ("1day") -> output = Intervals.ONE_DAY.getKey() + "=" +
                    Intervals.ONE_DAY.getValue();
            case ("1week") -> output = Intervals.ONE_WEEK.getKey() + "=" +
                    Intervals.ONE_WEEK.getValue();
            case ("1month") -> output = Intervals.ONE_MONTH.getKey() + "=" +
                    Intervals.ONE_MONTH.getValue();
        }
        return output;
    }

    private String setApiKey() {
        return ApiKey.API_KEY.getKey() + "=" + ApiKey.API_KEY.getValue();
    }

    private String setSymbol(String symbol) {
        return "symbol=" + symbol + "&";
    }

    private String setOutputSize() {
        return "&" + OutputSize.OUTPUT_SIZE.getKey() + "=" + OutputSize.OUTPUT_SIZE.getValue();
    }

}

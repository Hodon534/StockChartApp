package com.javafxstockchart.service;

import com.javafxstockchart.model.Pojo.TimeSeries.PojoTimeSeries;
import com.javafxstockchart.model.Pojo.TimeSeries.StockQueryLinkCreator;
import com.javafxstockchart.repository.ParseJsonToPOJO;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


/**
 * Make a request from TwelveData and return POJO object of Time Series
 */
@RestController
public class GetTimeSeriesJSON {

    public static PojoTimeSeries requestData(String interval, String symbol) throws IOException {
        StockQueryLinkCreator stockQueryLinkCreator = new StockQueryLinkCreator();
        URL url = new URL(stockQueryLinkCreator.createLink(interval, symbol));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        if (connection.getResponseCode() != 200) {
            throw new RuntimeException("HttpResponseCode: " + connection.getResponseCode());
        } else {
            StringBuilder requestOutput = getStringBuilderFromJson(url);
            return ParseJsonToPOJO.parseJsonToObject(requestOutput.toString());
        }
    }

    @NotNull
    private static StringBuilder getStringBuilderFromJson(URL url) throws IOException {
        StringBuilder requestOutput = new StringBuilder();
        Scanner scanner = new Scanner(url.openStream());
        while (scanner.hasNext()) {
            requestOutput.append(scanner.nextLine());
        }
        scanner.close(); //
        return requestOutput;
    }

}

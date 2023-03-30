package com.javafxstockchart.service;

import com.javafxstockchart.model.Pojo.TimeSeries.PojoTimeSeries;
import com.javafxstockchart.model.Pojo.TimeSeries.StockQueryLinkCreator;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@RestController
public class GetStockQuery {

    public static PojoTimeSeries requestData(String interval, String symbol) throws IOException {
        StockQueryLinkCreator stockQueryLinkCreator = new StockQueryLinkCreator();
        URL url = new URL(stockQueryLinkCreator.createLink(interval, symbol));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        int responseCode = connection.getResponseCode();
        // 200 OK
        if (responseCode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responseCode);
        } else {
            StringBuilder requestOutput = new StringBuilder();
            Scanner scanner = new Scanner(url.openStream());
            while (scanner.hasNext()) {
                requestOutput.append(scanner.nextLine());
            }
            scanner.close(); //
            return ParseJsonToSymbolList.parseJsonToObject(requestOutput.toString());
        }
    }

}